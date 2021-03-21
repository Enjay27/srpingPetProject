package learning.petProject.prototype.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Based {

    @Id
    @GeneratedValue
    private Long id;

    String name;

    public Based(String name) {
        this.name = name;
    }
}
