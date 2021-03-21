package learning.petProject.entity.content;

import javax.jdo.annotations.Discriminator;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@Discriminator("Q")
public class Question extends Content {

    @Enumerated
    QuestionType questionType;
}
