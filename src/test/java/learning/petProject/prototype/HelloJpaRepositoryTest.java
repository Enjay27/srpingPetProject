package learning.petProject.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class HelloJpaRepositoryTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    HelloJpaRepository helloJpaRepository;

    @BeforeEach
    public void initData() {
        Hello a = new Hello("a");
        Hello b = new Hello("b");
        Hello c = new Hello("c");
        Hello d = new Hello("d");

        em.persist(a);
        em.persist(b);
        em.persist(c);
        em.persist(d);
    }


    @Test
    public void save() {
        Hello e = new Hello("e");
        helloJpaRepository.save(e);

        Hello findHello = em.find(Hello.class, e.getId());
        assertThat(findHello.getData()).isEqualTo("e");
    }

    @Test
    public void findAll() {
        List<Hello> allHello = helloJpaRepository.findAll();
        assertThat(allHello).extracting("data").containsExactly("a", "b", "c", "d");
    }

    @Test
    public void findById() {
        Hello e = new Hello("e");

        em.persist(e);

        Hello findHello = helloJpaRepository.findById(e.getId()).get();

        assertThat(findHello.getData()).isEqualTo(e.getData());
    }

    @Test
    public void delete() {
        Hello e = new Hello("e");

        em.persist(e);

        Hello beforeDelete = em.find(Hello.class, e.getId());
        assertThat(beforeDelete).isNotNull();
        assertThat(beforeDelete.getData()).isEqualTo("e");

        helloJpaRepository.delete(e);

        Hello afterDelete = em.find(Hello.class, e.getId());
        assertThat(afterDelete).isNull();
    }

    @Test
    public void count() {
        long count = helloJpaRepository.count();
        assertThat(count).isEqualTo(4);

        Hello e = new Hello("e");

        em.persist(e);

        count = helloJpaRepository.count();
        assertThat(count).isEqualTo(5);
    }


}