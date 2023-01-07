package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
@Configuration
public class AppConfig {

    //생성자 주입을 통한 DIP(의존관계 역전 원칙) 를 만족하는 객체 지향 방법
    //생성한 객체 인스턴스의 참조를 생성자를 통해 주입한다.

    @Bean(name ="memberService")//bean의 이름은 항상 중복되지 않게 다른 이름을 부여해야 한다.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
