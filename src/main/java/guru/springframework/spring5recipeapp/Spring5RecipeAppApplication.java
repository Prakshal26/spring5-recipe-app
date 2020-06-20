/*

ENtity Relationship
Recipe to Notes-----One to One
Recipe to cateogry --- Many to many ie Recipe can have many Cateogary and a cateogary can have many recipe
recipe to Ingridients ----One to many

Ingridents to unit of measure -----one to one

Each Recipe has a difficulty level associated with. So we will create a enum inside domain and
have three level of difficulties like easy,moderate and Hard.

 */
/*
In video 139 we have initialized the data in data.sql file under resources. We need to go to
localhost:8080/h2-console and can see the data being added
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
