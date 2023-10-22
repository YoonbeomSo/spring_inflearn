package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000")//Object Error 처리방법
//but, jdk14 이후 버전부터는 javascript가 지원되지 않는 GraalVM 을 사용 -> @ScriptAssert 는 현재 사용할 수 없는 기능이다.
public class Item {

//    @NotNull(groups = UpdateCheck.class) //수정 요구사항 추가
    private Long id;
//    @NotBlank(message = "공백x", groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;
//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price; //A -> TypeMismatch

//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
