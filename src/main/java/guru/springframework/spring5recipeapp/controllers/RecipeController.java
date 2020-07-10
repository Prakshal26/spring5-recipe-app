package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.exceptions.NotFoundException;
import guru.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by jt on 6/19/17.
 */
@Slf4j
@Controller
public class RecipeController {

    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
/*
We are getting id from index.html and based on id we need to fetch that particular recipe and
give the content of that recipe to show.html.
 */
    @GetMapping
    @RequestMapping("/recipe/{id}/show") //We have to pick the id value from the URL.
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }
    /*
    If we want to create a new form. It will store the recipe
     */
    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return  "recipe/recipeform";
    }

    /*
    ModelAttribute binds the Post mapping to Recipe command object.
    When we will submit the form this will be called.
     */
    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return RECIPE_RECIPEFORM_URL;
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        //Redirect tells the spring to redirect to specific url.
        /*
        As soon as we have submitted the form we have to go to some page, so by this re-direct
        we will go to/recipe/show after submitting the form.
         */
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

}