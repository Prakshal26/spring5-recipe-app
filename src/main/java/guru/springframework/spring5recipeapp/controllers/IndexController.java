package guru.springframework.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
USe OF Controller- in main application page ie Spring5RecipeAppApplication we have given SpringApplication
at the start of the class. So that will look for Controller so we have given it controller.
 */
@Controller
public class IndexController {

    /*
    Request Mapping means it will be called when we will give index url in browser or homepage.
     */
    @RequestMapping({"","/","/index"})
    public String getIndexPage() {
        //Return index means it will send the request back to index page i.e to resources/templates/index
        //this is the Thymleaf template this keyword index must match with index.html page name.
        System.out.println("fjdd");
        return "index";
    }
}
