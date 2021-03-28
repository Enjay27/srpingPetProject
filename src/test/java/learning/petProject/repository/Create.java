package learning.petProject.repository;

import learning.petProject.entity.content.*;
import learning.petProject.entity.member.Member;
import learning.petProject.entity.member.MemberStatus;
import learning.petProject.entity.member.MemberType;
import learning.petProject.entity.reply.Reply;
import learning.petProject.entity.reply.ReplyStatus;

import java.util.Arrays;

public class Create {

    static Member member(String id, String password, String name, MemberStatus status, MemberType type) {
        Member member = new Member();
        member.setLogin_id(id);
        member.setPassword(password);
        member.setName(name);
        member.setStatus(status);
        member.setType(type);
        return member;
    }

    static Content freeContent(String title, String content, ContentStatus status, Member member) {
        Content created = new Free();
        created.setTitle(title);
        created.setContent(content);
        created.setStatus(status);
        created.setMember(member);
        return created;
    }

    static Content photoContent(String title, String content, ContentStatus status, Member member) {
        Content created = new Photo();
        created.setTitle(title);
        created.setContent(content);
        created.setStatus(status);
        created.setMember(member);
        return created;
    }

    static Content questionContent(String title, String content, ContentStatus status, Member member) {
        Question created = new Question();
        created.setTitle(title);
        created.setContent(content);
        created.setStatus(status);
        created.setMember(member);
        created.setQuestionType(Arrays.stream(QuestionType.values()).findAny().get());
        return created;
    }

    static Content walkingContent(String title, String content, ContentStatus status, Member member) {
        Content created = new Walking();
        created.setTitle(title);
        created.setContent(content);
        created.setStatus(status);
        created.setMember(member);
        return created;
    }

    static Reply parentReply(String reply, Content content, Member member, ReplyStatus status) {
        Reply created = new Reply();
        created.setReply(reply);
        created.setContent(content);
        created.setMember(member);
        created.setStatus(status);

        return created;
    }

    static Reply childReply(String reply, Reply parent, Member member, ReplyStatus status) {
        Reply created = new Reply();
        created.setParent(parent);
        created.setContent(parent.getContent());
        created.setReply(reply);
        created.setMember(member);
        created.setStatus(status);
        parent.getChild().add(created);

        return created;
    }
}
