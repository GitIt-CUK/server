package shop.gitit.core.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.gitit.core.exception.ExceptionEnum;
import shop.gitit.core.template.ErrorMessage;
import shop.gitit.infra.exception.FailConnectToGitHubException;
import shop.gitit.infra.exception.FailGetGitHubAccessTokenException;
import shop.gitit.infra.exception.FailGetGitHubUserInfoException;

@RestControllerAdvice
public class InfraExceptionHandler {

    @Deprecated // 현재는 사용하고 있지 않은 라이브러리입니다.
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> failConnectToGitHubException(
            FailConnectToGitHubException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.FAIL_CONNECT_TO_GITHUB.getErrorCode())
                                .message(ExceptionEnum.FAIL_CONNECT_TO_GITHUB.getMessage())
                                .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> failGetGitHubAccessTokenException(
            FailGetGitHubAccessTokenException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.FAIL_GET_GITHUB_ACCESSTOKEN.getErrorCode())
                                .message(ExceptionEnum.FAIL_GET_GITHUB_ACCESSTOKEN.getMessage())
                                .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> failGetGitHubUserInfoException(
            FailGetGitHubUserInfoException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.FAIL_GET_GITHUB_USER_INFO.getErrorCode())
                                .message(ExceptionEnum.FAIL_GET_GITHUB_USER_INFO.getMessage())
                                .build());
    }
}
