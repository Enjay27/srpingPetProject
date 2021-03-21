package learning.petProject.prototype.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DerivedB extends Based {
    public DerivedB(String basedB) {
        super(basedB);
    }
}
