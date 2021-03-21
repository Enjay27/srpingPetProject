package learning.petProject.entity.member;

import learning.petProject.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @PersistenceContext
    EntityManager em;

    MemberRepository memberRepository;

    @Test
    @Rollback(false)
    public void initDBTest() {
        Member member1 = createMember("userA", "1234", "AA", MemberStatus.ACTIVE, MemberType.REGULAR);
        em.persist(member1);
    }

    private Member createMember(String id, String password, String name, MemberStatus status, MemberType type) {
        Member member = new Member();
        member.setLogin_id(id);
        member.setPassword(password);
        member.setName(name);
        member.setStatus(status);
        member.setType(type);
        return member;
    }

}