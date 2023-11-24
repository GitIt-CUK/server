package shop.gitit.core.exception;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    // MEMBER 600
    ALREADY_WITHDRAWN(600, "이미 탈퇴한 회원입니다."),
    NO_NICKNAME(601, "닉네임은 필수 입력 값입니다."),
    NICKNAME_LENGTH_VIOLATION(602, "닉네임은 최소 1글자 이상, 최대 6글자까지 가능합니다."),
    NO_MEMBER(603, "존재하지 않는 회원입니다."),

    // GITHUB 700
    COMMIT_COUNT_VIOLATION(700, "누적 커밋 수는 최소 0이상이야 합니다."),
    NO_MATCHING_COLOR_CODE(701, "컬러코드가 일치하지 않습니다."),
    NO_GITHUB_INFO(702, "깃허브 프로필이 존재하지 않습니다."),

    // PAYMENT 800
    NO_WALLET(700, "지갑 정보가 없습니다."),

    // SHOP 900
    IS_NULL(1000, "필수 입력값이 누락되었습니다."),
    ;

    private int errorCode;
    private String message;

    ExceptionEnum(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
