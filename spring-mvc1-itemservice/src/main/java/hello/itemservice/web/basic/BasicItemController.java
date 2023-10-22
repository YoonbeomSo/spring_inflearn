package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }

    /**
     * 상품 상세 폼
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }


    /**
     * 등록 폼
     */
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    /**
     * 등록 로직1
     * @RequestParam 을 사용해 데이터를 받는다.
     */
//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model
    ) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * 등록 로직2
     * @ModelAttribute 를 사용해 dto로 데이터를 받는다.
     */
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
//        model.addAttribute("item", item); //자동 추가, 생략 가능
        return "basic/item";
    }

    /**
     * 등록 로직3
     * @ModelAttribute 를 사용해 dto로 데이터를 받는다.
     * parmeter 의 첫글자를 소문자로 변경하면 이름 지정을 생략하여 사용가능하다.
     */
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * 등록 로직4
     * ModelAttribute 는 생략 가능하다.
     */
//    @PostMapping("/add")
    public String addItemV4(Item item) { //ModelAttribute 생략 가능
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * 등록 로직5
     * post 요청 후 화면단에서 새로 고침을 하면 마지막에 사용된 post 요청이 계속해서 전송된다. -> post buiseness 로직 중복 수행
     * 이를 해결하기 위해 post 요청 후 redirect로 get 요청을 다시 수행하게 되면,
     * 새로 고침을 해도 마지막에 호출된 get 요청이 전송되어 문제가 해결된다.
     * 이러한 방법을 PRG(PostRedirectGet)이라고 한다.
     */
//    @PostMapping("/add")
    public String addItemV5(Item item) { //ModelAttribute 생략 가능
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    /**
     * 등록 로직6
     * PRG 방식에서 RedirectAttributes를 사용하면 ModelAttribute와 같이
     * 화면 단으로 모델을 넘길 수 있다.
     */
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) { //ModelAttribute 생략 가능
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";

    }

    /**
     * 수정폼
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }


    /**
     * 수정 로직
     * redirect 에서도 pathVariable 형태를 사용할 수 있다.
     */
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }




}

