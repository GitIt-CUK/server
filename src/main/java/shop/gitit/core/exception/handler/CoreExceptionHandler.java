package shop.gitit.core.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.gitit.core.exception.ExceptionEnum;
import shop.gitit.core.template.ErrorMessage;

@RestControllerAdvice
public class CoreExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> illegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.IS_NULL.getErrorCode())
                                .message(ExceptionEnum.IS_NULL.getMessage())
                                .build());
    }
}
