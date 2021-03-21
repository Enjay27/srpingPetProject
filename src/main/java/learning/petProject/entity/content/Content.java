package learning.petProject.entity.content;

import learning.petProject.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "contentType")
@Entity
@Getter
@Setter
public abstract class Content {

    @GeneratedValue
    @Id
    @Column(name = "content_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    String title;
    String content;
    int views;
    int likes;
    String picture;

    @Enumerated(EnumType.STRING)
    ContentStatus status;
}
