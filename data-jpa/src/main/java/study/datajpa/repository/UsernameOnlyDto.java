package study.datajpa.repository;

public class UsernameOnlyDto {

    private final String username;


    // 생성자 파라미터 이름을 Entity 와 매칭하여 정확하게 입력해야함.
    public UsernameOnlyDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
