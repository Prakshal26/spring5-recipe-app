package guru.springframework.spring5recipeapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
