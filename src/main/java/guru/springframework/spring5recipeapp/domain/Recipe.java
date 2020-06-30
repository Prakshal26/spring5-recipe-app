package guru.springframework.spring5recipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    /*
    This is kind of Data base we are making here. So this one of the table that's why we have
    given Entity on top.
    This is the id by which we will identify this table and we are giving GenerationType.Identity
    which will generate id automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set <Ingredient> ingredients = new HashSet<>();


    @Lob
    private Byte[] image;
    //Every recipe has some difficulty so created Enum for it.
    /*
    One thing to note is EnumType can be String or Ordinal.Ordinal is default that is it
    will start indexing and wil take Easy as 1, Moderate as 2 and so on. But String will not
    do the indexing instead it will pick it as it is i.e. everywhere it will use EASY as EASY only.
    Will not think easy as 1 but instead will use EASY as EASY only and HARD as HARD.
     */
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    /*
    Here we are making Cascade type All that means we are deleting the recipe then we should
    also delete its notes. So automatically it will delete the notes we are deleting recipe.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    /*
    It is manytomany so we will get many values so we need a SET.
     */
    @ManyToMany
    @JoinTable(name = "recipe_category",//In H2 DB console we will have table by this name recipe_cateogary
    joinColumns = @JoinColumn(name= "recipe_id"),//The table will have one feild recipe id and one field category id
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set <Category> categories =new HashSet<>();

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }
    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
