package shop.gitit.github.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.gitit.core.exception.ExceptionEnum;
import shop.gitit.core.template.ErrorMessage;
import shop.gitit.github.exception.CommitCountViolationException;
import shop.gitit.github.exception.NoMatchingColorCodeException;
import shop.gitit.member.exception.NoMemberException;
import shop.gitit.payment.exception.NoWalletException;

@RestControllerAdvice(basePackages = {"shop.gitit.github"})
public class GitHubExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> noClassroomException(CommitCountViolationException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.COMMIT_COUNT_VIOLATION.getErrorCode())
                                .message(ExceptionEnum.COMMIT_COUNT_VIOLATION.getMessage())
                                .build());
    }

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
    public ResponseEntity<ErrorMessage> noMemberException(NoMemberException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.NO_MEMBER.getErrorCode())
                                .message(ExceptionEnum.NO_MEMBER.getMessage())
                                .build());
    }
}
