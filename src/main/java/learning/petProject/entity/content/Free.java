package learning.petProject.entity.content;

import javax.jdo.annotations.Discriminator;
import javax.persistence.Entity;

@Entity
@Discriminator("F")
public class Free extends Content {
}
