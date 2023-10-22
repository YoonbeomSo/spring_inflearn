package jpabook.jpashop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) //delivery 보다는 order 로 조회할 경우가 많기 때문에 1대1관계에서 order 를 연관 관계 주인으로 두었다
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)  //ORDINAL:숫자로 들어가서 쓰지말아야함 -> EnumType은 꼭 STRING으로 사용
    private DeliveryStatus status;  //배송 상태[READY, COMP]


}
