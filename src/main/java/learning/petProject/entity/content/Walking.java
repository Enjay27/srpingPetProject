package learning.petProject.entity.content;

import javax.jdo.annotations.Discriminator;
import javax.persistence.Entity;

@Entity
@Discriminator("W")
public class Walking extends Content {

    String map;
}
