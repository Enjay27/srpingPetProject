package learning.petProject.repository;

import learning.petProject.entity.content.Content;
import learning.petProject.entity.content.ContentStatus;
import learning.petProject.entity.member.Member;
import learning.petProject.entity.member.MemberStatus;
import learning.petProject.entity.member.MemberType;
import learning.petProject.entity.reply.Reply;
import learning.petProject.entity.reply.ReplyStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReplyRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void createOne() {
        Member member = Create.member("AA", "userA", "1234", MemberStatus.ACTIVE, MemberType.REGULAR);
        Content content = Create.freeContent("free title", "free content", ContentStatus.VIEWABLE, member);
        Reply reply = Create.parentReply("Test", content, member, ReplyStatus.VIEWABLE);
        replyRepository.save(reply);

        Reply findReply = em.find(Reply.class, reply.getId());
        assertThat(findReply.getReply()).isEqualTo("Test");
    }

    @Test
    public void deleteOne() {
        Member member = Create.member("AA", "userA", "1234", MemberStatus.ACTIVE, MemberType.REGULAR);
        Content content = Create.freeContent("free title", "free content", ContentStatus.VIEWABLE, member);
        Reply reply = Create.parentReply("Test", content, member, ReplyStatus.VIEWABLE);
        em.persist(member);
        em.persist(content);
        em.persist(reply);

        Reply findReply = em.find(Reply.class, reply.getId());

        assertThat(reply).isEqualTo(findReply);

        replyRepository.delete(reply);

        findReply = em.find(Reply.class, reply.getId());
        assertThat(findReply).isNull();
    }

    @Test
    @Rollback(false)
    public void deleteChildren() {
        Member member = Create.member("AA", "userA", "1234", MemberStatus.ACTIVE, MemberType.REGULAR);
        Content content = Create.freeContent("free title", "free content", ContentStatus.VIEWABLE, member);
        Reply parentReply = Create.parentReply("Test", content, member, ReplyStatus.VIEWABLE);
        List<Reply> childReplies = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            childReplies.add(Create.childReply("child" + i, parentReply, member, ReplyStatus.VIEWABLE));
        }
        em.persist(member);
        em.persist(content);
        em.persist(parentReply);
        for (Reply childReply : childReplies) {
            em.persist(childReply);
        }

        Reply findParentReply = em.find(Reply.class, parentReply.getId());
        assertThat(findParentReply.getChild()).extracting("reply").containsExactly("child1", "child2", "child3");
        assertThat(findParentReply.getChild()).extracting("id").containsExactly(childReplies.get(0).getId(), childReplies.get(1).getId(), childReplies.get(2).getId());

        assertThat(replyRepository.findById(childReplies.get(0).getId())).isEqualTo(Optional.of(childReplies.get(0)));
        replyRepository.delete(childReplies.get(0));
        Optional<Reply> findOne = replyRepository.findById(childReplies.get(0).getId());
        assertThat(findOne).isEqualTo(Optional.empty());
        replyRepository.deleteAll(findParentReply.getChild());

        List<String> childReplyList = replyRepository.findById(parentReply.getId()).get().getChild().stream().map(Reply::getReply).collect(Collectors.toList());
        assertThat(childReplyList).doesNotContain("child1", "child2", "child3");
    }

    @Test
    public void test() {
        //replyRepository.findByMemberId();
    }
}