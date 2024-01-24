package study.datajpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item implements Persistable<String>  {

//    @Id
//    @GeneratedValue
//    private Long id;

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    public Item(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    //GeneratedValue 가 아닌 id 생성방법의 entity를 save 호출 시, isNew() 함수에서 merge 가 동작하여 select query 가 발생하는 문제가 발생
    //해결 방안 -> Persistable<?> 인터페이스를 상속받아 isNew() 함수를 Override 하여 구현한다.
    @Override
    public boolean isNew() {
//        return false;
        return createdDate == null; //등록기간을 조합해서 구현하면 효율적이다. -> 아마 uuid 를 사용한 id 생성전략에서 사용할 수 있을것 같다.
    }
}
