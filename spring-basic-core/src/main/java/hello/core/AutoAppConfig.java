package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //범위 지정 (Default 는 @ComponentScan 이 붙은 package 의 하위)
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        //예제를 위해 ComponentScan 에서 AppConfig 를 제외 하기 위함
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 중복 되는 수동 빈을 등록할 시 boot 에서는 수동을 우선순위로 가져 간다
    // -> 하지만 개발자의 의도와 다르게 흘러갈 가능성이 높기 때문에 자동 등록만 이용 하도록 한다.
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
