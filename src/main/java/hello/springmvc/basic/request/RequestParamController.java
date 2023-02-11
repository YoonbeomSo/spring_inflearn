package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller //return view를 찾는다
//@RestController //return Http body에 바로 담긴다.
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParmV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParmV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParmV4(
            String username,
            int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParmRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParmDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParmMap(@RequestParam Map<String, Object> paramMap){

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

}
