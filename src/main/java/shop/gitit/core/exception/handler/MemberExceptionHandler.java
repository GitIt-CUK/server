package shop.gitit.core.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.gitit.core.exception.ExceptionEnum;
import shop.gitit.core.template.ErrorMessage;
import shop.gitit.member.exception.*;

@RestControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> alreadyWithdrawnException(AlreadyWithdrawnException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.ALREADY_WITHDRAWN.getErrorCode())
                                .message(ExceptionEnum.ALREADY_WITHDRAWN.getMessage())
                                .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> nicknameLengthViolationException(
            NicknameLengthViolationException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.NICKNAME_LENGTH_VIOLATION.getErrorCode())
                                .message(ExceptionEnum.NICKNAME_LENGTH_VIOLATION.getMessage())
                                .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> NoMemberException(NoMemberException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.NO_MEMBER.getErrorCode())
                                .message(ExceptionEnum.NO_MEMBER.getMessage())
                                .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> noRefreshTokenException(NoRefreshTokenException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.NO_REFRESH_TOKEN.getErrorCode())
                                .message(ExceptionEnum.NO_REFRESH_TOKEN.getMessage())
                                .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> noMatchingRefreshToken(NoMatchingRefreshTokenException e) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorMessage.builder()
                                .errorCode(ExceptionEnum.NO_MATCHING_REFRESH_TOKEN.getErrorCode())
                                .message(ExceptionEnum.NO_MATCHING_REFRESH_TOKEN.getMessage())
                                .build());
    }
}
