package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

    /**
     *  안좋은 예 : 객체를 직접 리턴하는 방법이기 때문에 사용하면 안됨
     *  proxy 객체 빈으로 등록 -> 지연 로딩 객체 null 값으로 리턴 가능
     */
    @Bean
    Hibernate5Module hibernate5Module(){
        return new Hibernate5Module();
    }

}
