package learning.petProject.entity.content;

import javax.jdo.annotations.Discriminator;
import javax.persistence.Entity;

@Entity
@Discriminator("P")
public class Photo extends Content {
}
