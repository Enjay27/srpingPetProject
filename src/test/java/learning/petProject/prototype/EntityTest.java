package learning.petProject.prototype;

import learning.petProject.prototype.entity.Based;
import learning.petProject.prototype.entity.DerivedA;
import learning.petProject.prototype.entity.DerivedB;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
public class EntityTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @Rollback(false)
    public void mapper() {
        Based based = new Based("based");
        em.persist(based);

        Based basedA = new DerivedA("basedA");
        em.persist(basedA);

        Based basedB = new DerivedB("basedB");
        em.persist(basedB);

        Based basedC = new DerivedA("basedC");
        em.persist(basedC);

        em.flush();

        Based findBased = em.find(Based.class, based.getId());
        Based findBasedA = em.find(Based.class, basedA.getId());
        Based findBasedB = em.find(Based.class, basedB.getId());
        Based findBasedC = em.find(Based.class, basedC.getId());
        System.out.println("findBased = " + findBased.getId());
        System.out.println("findBasedA = " + findBasedA.getId());
        System.out.println("findBasedB = " + findBasedB.getId());
        System.out.println("findBasedC= " + findBasedC.getId());

        ModelMapper modelMapper = new ModelMapper();

        DerivedA mappedBased = modelMapper.map(findBased, DerivedA.class);
        em.persist(mappedBased);

        DerivedA mappedB = modelMapper.map(findBasedB, DerivedA.class);
        em.persist(mappedB);
    }
}
