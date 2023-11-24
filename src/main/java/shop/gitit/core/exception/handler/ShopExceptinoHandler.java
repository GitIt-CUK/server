package shop.gitit.core.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.gitit.core.exception.ExceptionEnum;
import shop.gitit.core.template.ErrorMessage;
import shop.gitit.shop.exception.NoMatchingItemTypeException;

@RestControllerAdvice
public class ShopExceptinoHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> noMatchingItemTypeException(NoMatchingItemTypeException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.NO_MATCHING_ITEM_TYPE.getErrorCode())
                                .message(ExceptionEnum.NO_MATCHING_ITEM_TYPE.getMessage())
                                .build());
    }
}
