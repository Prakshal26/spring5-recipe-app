package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/*
USe OF Controller- in main application page ie Spring5RecipeAppApplication we have given SpringApplication
at the start of the class. So that will look for Controller so we have given it controller.
 */
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    /*
        Request Mapping means it will be called when we will give index url in browser or homepage.
         */
    @RequestMapping({"","/","/index"})
    public String getIndexPage() {

        //We have a added Category American in data.sql file. We are fething American from the DB.
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        /*
        Now we are geting id corresponding to American and Unit of MEasure.
        One thing to notice is that we have not stored ID manually but if we see Category class then we can
        see we have @Id annotation on top of ID data member and it is generating from there automatically.
        Now here we are printing that id.
         */
        System.out.println("Cateogry id is = "+ categoryOptional.get().getId());
        System.out.println("UOM id is = " + unitOfMeasureOptional.get().getId());

        //Return index means it will send the request back to index page i.e to resources/templates/index
        //this is the Thymleaf template this keyword index must match with index.html page name.
        System.out.println("fjdd");
        return "index";
    }
}
