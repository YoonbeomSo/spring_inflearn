package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LogFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request; //down casting
        String requestURI = httpRequest.getRequestURI();
        if(PatternMatchUtils.simpleMatch("/css/*", requestURI)){
            chain.doFilter(request, response);
        }else{
            log.info("LogFilter>>>doFilter");

            String uuid = UUID.randomUUID().toString();

            try {
                log.info("REQUEST [{}][{}]", uuid, requestURI);
                chain.doFilter(request, response);//다음 필터 호출(중요)
            } catch (Exception e) {
                throw e;
            } finally {
                //모든 필터 호출 후 호출됨.
                log.info("RESPONSE[{}][{}]", uuid, requestURI);
            }
        }

    }

    @Override
    public void destroy() {
        log.info("LogFilter>>>destroy");
    }
}
