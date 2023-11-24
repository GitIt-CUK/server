package shop.gitit.github.domain.grasscolor;

import java.util.Arrays;
import lombok.Getter;
import shop.gitit.core.exception.ExceptionEnum;
import shop.gitit.github.exception.NoMatchingColorCodeException;

@Getter
public enum GrassColor {
    GREEN("GREEN"),
    NORMAL("NORMAL"),
    SPECIAL("SPECIAL"),
    RARE("RARE"),
    ELITE("ELITE"),
    ;

    private String code;

    GrassColor(String code) {
        this.code = code;
    }

    public static GrassColor findColorByCode(String code) {
        return Arrays.stream(GrassColor.values())
                .filter(color -> code.equals(color.getCode()))
                .findAny()
                .orElseThrow(
                        () ->
                                new NoMatchingColorCodeException(
                                        ExceptionEnum.NO_MATCHING_COLOR_CODE.getMessage()));
    }

    public static GrassColor init() {
        return GrassColor.GREEN;
    }
}
