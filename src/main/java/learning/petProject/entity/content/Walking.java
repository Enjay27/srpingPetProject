package learning.petProject.entity.content;

import lombok.Data;

import javax.jdo.annotations.Discriminator;
import javax.persistence.Entity;

@Entity
@Data
@Discriminator("W")
public class Walking extends Content {

    String map;
}
