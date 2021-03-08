package learning.petProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HelloEntity {

    @Id @GeneratedValue
    Long id;

    private String data;
}
