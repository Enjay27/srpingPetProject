package learning.petProject.prototype;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HelloRepositoryTest {

    @Autowired
    HelloRepository helloRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    public void basicCRUD() {
        Hello hello1 = new Hello("hello1");
        Hello hello2 = new Hello("hello2");
        helloRepository.save(hello1);
        helloRepository.save(hello2);

        Optional<Hello> findHello1 = helloRepository.findById(hello1.getId());
        Optional<Hello> findHello2 = helloRepository.findById(hello2.getId());

        assertThat(findHello1.orElse(new Hello("empty"))).isEqualTo(hello1);
        assertThat(findHello2.orElse(new Hello("empty"))).isEqualTo(hello2);

        List<Hello> all = helloRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).extracting("data").containsExactly("hello1", "hello2");

        assertThat(helloRepository.count()).isEqualTo(2);

        helloRepository.delete(helloRepository.getOne(hello1.getId()));
        helloRepository.delete(helloRepository.getOne(hello2.getId()));

        assertThat(helloRepository.count()).isEqualTo(0);

    }

}