package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 *   @RestController 란 ?
 *   @Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다.
 *   그래서 뷰를 찾고 뷰가 랜더링 된다.
 *   @RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
 *   따라서 실행 결과로 ok 메세지를 받을 수 있다.
 *   @ResponseBody 와 관련이 있는데, 뒤에서 더 자세히 설명한다.
 */
@RestController
@Slf4j //log 선언
public class LogTestController {

    /**log 선언*/
    private final Logger log = LoggerFactory.getLogger(getClass());


/**
    logging level 은 application.properties 에서
     hello.springmvc 패키지와 그 하위 로그 레벨 설정
    logging.level.hello.springmvc = debug
*/
    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        /**
         * LEVEL: TRACE > DEBUG > INFO > WARN > ERROR 개발 서버는 debug 출력
         * 운영 서버는 info 출력
         * */
        log.trace("trace log={}", name);        //로컬에서 확인할때
        log.debug("debug log={}", name);        //개발서버에서 확인할때
        log.info("info log={}", name);          //운영서버에서 확인할때
        log.warn("warn log={}", name);
        log.error(" error log = {}", name);

        /**
        * 아래 두개의 로그가 다른 이유는 +하기 연산이 이루어지기 때문이다.
        * 해당 로그 레벨이 아닌 경우
        * 출력하지 않는 경우, 필요없는 리소스를 사용하게 된다.
        * 될수 있으면 아래 방식으로 사용하자.
        * */
        log.trace("trace log=" + name);
        log.trace("trace log={}", name);

        return "ok";
    }

}
