package learning.petProject.repository;

import learning.petProject.entity.member.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberByName(String name);

    Optional<Member> findOptionalByName(String name);

    Slice<Member> findByNameLike(String name, Pageable pageable);

    List<Member> findListByName(String username);
}
