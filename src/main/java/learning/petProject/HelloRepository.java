package learning.petProject;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class HelloRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public HelloRepository(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
    }
}
