package learning.petProject.entity.reply;

import learning.petProject.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Reply {

    @GeneratedValue
    @Id
    @Column(name = "reply_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    Reply parent;

    @OneToMany(mappedBy = "parent")
    List<Reply> child = new ArrayList<>();

    String reply;

    @Enumerated(EnumType.STRING)
    ReplyStatus status;
}
