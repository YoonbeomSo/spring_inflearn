package jpabook.jpashop.domain.item;

import jpabook.jpashop.Exception.NotEnoughStockException;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속 전략 선택
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item { //상속 관계 매핑

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


    //==비즈니스 로직==// --> setter를 지우고 변수를 변경하려면 핵심 비즈니스 로직을 가지고 변경시켜야 한다.
    /*stock 증가*/
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /*stock 감소*/
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }



}
