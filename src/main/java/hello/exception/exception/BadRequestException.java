package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad") //ResponseStatusExceptionResolver 에서 처리
public class BadRequestException extends RuntimeException {

}
