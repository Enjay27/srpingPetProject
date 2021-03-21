package learning.petProject.entity.content;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.jdo.annotations.Discriminator;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@Setter
@Getter
@Discriminator("Q")
public class Question extends Content {

    @Enumerated
    QuestionType questionType;
}
