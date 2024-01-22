package study.datajpa.dto;

import lombok.Data;
import study.datajpa.entity.Member;

@Data
public class MemberDto {

    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }

    //dto 는 entity 를 참조해 사용해도 된다.(필드로 선언x)
    public MemberDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
    }
}
