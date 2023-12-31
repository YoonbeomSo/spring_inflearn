package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface MemberRepository {
    Member save(Member member);
    Member findById(String memberId);
    void update(String memberId, int money);
    void delete(String memberId);
}
