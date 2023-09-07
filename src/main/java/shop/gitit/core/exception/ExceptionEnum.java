package shop.gitit.core.exception;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    ALREADY_WITHDRAWN(600, "이미 탈퇴한 회원입니다."),
    NO_NICKNAME(601, "닉네임은 필수 입력 값입니다."),
    NICKNAME_LENGTH_VIOLATION(602, "닉네임은 최소 1글자 이상, 최대 6글자까지 가능합니다."),
    COMMIT_COUNT_VIOLATION(603, "누적 커밋 수는 최소 0이상이야 합니다."),
    IS_NULL(1000, "필수 입력값이 누락되었습니다."),
    ;

    private int errorCode;
    private String message;

    ExceptionEnum(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
