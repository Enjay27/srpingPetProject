package learning.petProject.entity.content;

import learning.petProject.entity.BaseEntity;
import learning.petProject.entity.member.Member;
import learning.petProject.entity.reply.Reply;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "contentType")
@Entity
@Getter
@Setter
public abstract class Content extends BaseEntity {

    @GeneratedValue
    @Id
    @Column(name = "content_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    @OneToMany(mappedBy = "content")
    List<Reply> replies = new ArrayList<>();

    String title;
    String content;
    int views;
    int likes;
    String picture;

    @Enumerated(EnumType.STRING)
    ContentStatus status;
}
