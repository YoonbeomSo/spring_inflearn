package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;


    @ManyToMany //다대다 관계는 실무에서는 사용하지 않는게 좋다.
    @JoinTable(name = "category_item", //다대다 관계에서는 중간테이블을 조인해주는 과정이 필요하다.
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();


    /*======연관관계 편의 메서드=====*/
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
