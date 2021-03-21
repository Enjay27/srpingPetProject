package learning.petProject.entity.admin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue
    @Column(name = "admin_id")
    Long id;

    String login_id;
    String password;
    String name;

    @Enumerated(EnumType.STRING)
    AdminLevel level;

    @Enumerated(EnumType.STRING)
    AdminStatus status;
}
