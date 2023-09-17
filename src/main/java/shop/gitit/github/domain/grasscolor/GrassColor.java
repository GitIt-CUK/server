package shop.gitit.github.domain.grasscolor;

import java.util.Arrays;
import lombok.Getter;

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
                .orElseThrow(IllegalArgumentException::new);
    }

    public static GrassColor init() {
        return GrassColor.GREEN;
    }
}
