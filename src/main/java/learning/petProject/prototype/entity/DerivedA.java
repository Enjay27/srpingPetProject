package learning.petProject.prototype.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DerivedA extends Based {

    public DerivedA(String basedA) {
        super(basedA);
    }
}
