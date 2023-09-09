package hello.springtx.propagation;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Slf4j
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String username;

    public Member() {
    }

    public Member(String username) {
        this.username = username;
    }
}
