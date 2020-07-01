package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



/*
USe OF Controller- in main application page ie Spring5RecipeAppApplication we have given SpringApplication
at the start of the class. So that will look for Controller so we have given it controller.
 */

@Slf4j
@Controller
public class IndexController {

    /*

    We have commented all these as now we have created services package so there we can do all this.


    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


        Request Mapping means it will be called when we will give index url in browser or homepage.

    @RequestMapping({"","/","/index"})
    public String getIndexPage() {

        //We have a added Category American in data.sql file. We are fething American from the DB.
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        Now we are geting id corresponding to American and Unit of MEasure.
        One thing to notice is that we have not stored ID manually but if we see Category class then we can
        see we have @Id annotation on top of ID data member and it is generating from there automatically.
        Now here we are printing that id.

        System.out.println("Cateogry id is = "+ categoryOptional.get().getId());
        System.out.println("UOM id is = " + unitOfMeasureOptional.get().getId());

        //Return index means it will send the request back to index page i.e to resources/templates/index
        //this is the Thymleaf template this keyword index must match with index.html page name.



        System.out.println("fjdd");
        return "index";


     */

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index page");//Features of Project lombok slf4j
        model.addAttribute("recipes",recipeService.getRecipe());

        return "index";

    }
}
