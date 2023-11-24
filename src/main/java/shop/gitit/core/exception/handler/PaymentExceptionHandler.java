package shop.gitit.core.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.gitit.core.exception.ExceptionEnum;
import shop.gitit.core.template.ErrorMessage;
import shop.gitit.github.exception.NoMatchingColorCodeException;
import shop.gitit.payment.exception.NoWalletException;
import shop.gitit.payment.exception.PointViolationException;

@RestControllerAdvice
public class PaymentExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> noWalletException(NoWalletException e) {

        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.NO_WALLET.getErrorCode())
                                .message(ExceptionEnum.NO_WALLET.getMessage())
                                .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> noMatchingColorCodeException(
            NoMatchingColorCodeException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.NO_MATCHING_COLOR_CODE.getErrorCode())
                                .message(ExceptionEnum.NO_MATCHING_COLOR_CODE.getMessage())
                                .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> pointViolationException(PointViolationException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.POINT_VIOLATION.getErrorCode())
                                .message(ExceptionEnum.POINT_VIOLATION.getMessage())
                                .build());
    }
}
