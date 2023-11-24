package shop.gitit.core.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.gitit.core.exception.ExceptionEnum;
import shop.gitit.core.template.ErrorMessage;
import shop.gitit.github.exception.CommitCountViolationException;
import shop.gitit.github.exception.NoGitHubInfoException;

@RestControllerAdvice
public class GitHubExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> commitCountViolationException(
            CommitCountViolationException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.COMMIT_COUNT_VIOLATION.getErrorCode())
                                .message(ExceptionEnum.COMMIT_COUNT_VIOLATION.getMessage())
                                .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> noGitHubInfoException(NoGitHubInfoException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.NO_GITHUB_INFO.getErrorCode())
                                .message(ExceptionEnum.NO_GITHUB_INFO.getMessage())
                                .build());
    }
}
