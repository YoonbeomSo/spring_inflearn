package hello.jdbc.service;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    void accountTransfer(String fromId, String toId, int money);
}
