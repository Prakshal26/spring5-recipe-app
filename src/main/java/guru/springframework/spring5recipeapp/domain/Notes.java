package guru.springframework.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    Here there is no need to Cascade type as we dont want recipe to be deleted if we delete the notes.
    Recipe can exist without the notes
     */
    @OneToOne
    private Recipe recipe;

    //By default spring allow 255 char but by lob we can store lot more than 255 chars.
    @Lob
    private String recipeNotes;

}
