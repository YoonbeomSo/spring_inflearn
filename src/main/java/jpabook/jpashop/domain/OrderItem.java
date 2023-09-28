package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //값이 없는 객체를 막는 생성자 생성
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 가격
    private int count; //주문 수량


    //===생성 메서드===//
    public static OrderItem createOrderItem(Item item, int orderPrce, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrce);
        orderItem.setCount(count);

        item.removeStock(count); //entity에 올라와있는 현재 재고에서 count만큼 감량
        return orderItem;
    }

    //===비즈니스 로직===//
    public void cancel() {
        getItem().addStock(count); //재고수량 원상복구
    }

    //===조회 로직===//

    /**
     * 주문상품 전체 가격 조회
     */
    public int getTotalprice() {
        return getOrderPrice() * getCount();
    }
}
