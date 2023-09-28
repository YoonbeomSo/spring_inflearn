package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        //merge는 전체 필드를 변경시키기 때문에 null의 위험이 있다 따라서 실무에서는 변경감지를 사용하는 것이 옳다.
        Item findItem = itemRepository.findOne(itemId); //같은 엔티티를 조회한다. ->findItem은 영속성Entity
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        //가급적이면 setter를 사용하지 않고, 의미가 부여되어있는 메소드를 사용하여 Entity의 값을 변경해야 값의 변경의 역추적이 가능하다
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
