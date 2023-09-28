package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue //sequence유사
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //order table 에 있는 "member" fild 에 의해 mapping 된것이라는 뜻 -> 연관 관계의 주인이 아닌 반대 쪽에 적어준다.
    private List<Order> orders = new ArrayList<>(); //collection은 필드에서 바로 초기해주는 것이 좋다.
}
