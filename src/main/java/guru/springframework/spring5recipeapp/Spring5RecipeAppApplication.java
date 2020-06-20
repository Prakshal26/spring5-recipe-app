/*

ENtity Relationship
Recipe to Notes-----One to One
Recipe to cateogry --- Many to many
recipe to Ingridients ----One to many

Ingridents to unit of measure -----one to one

 */

package guru.springframework.spring5recipeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring5RecipeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring5RecipeAppApplication.class, args);
    }

}
