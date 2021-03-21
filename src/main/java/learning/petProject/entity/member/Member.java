package learning.petProject.entity.member;

import learning.petProject.entity.content.Content;
import learning.petProject.entity.reply.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    Long id;

    @OneToMany(mappedBy = "member")
    List<Content> contents = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Reply> replies = new ArrayList<>();

    @NotEmpty
    String login_id;
    @NotEmpty
    String password;

    String name;
    String address;
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    MemberStatus status;

    @Enumerated(EnumType.STRING)
    MemberType type;
}
