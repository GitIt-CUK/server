package shop.gitit.github.domain.grasscolor;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum GrassColor {
    RED("RED"),
    WINE("WINE"),
    PURPLE("PURPLE"),
    VIOLET("VIOLET"),
    INDIGO("INDIGO"),
    BLUE("BLUE"),
    CYAN("CYAN"),
    TEAL("TEAL"),
    GREEN("GREEN"),
    LIME("LIME"),
    YELLOW("YELLOW"),
    ORANGE("ORANGE"),
    BRONZE("BRONZE"),
    SILVER("SILVER"),
    GOLD("GOLD"),
    PLATINUM("PLATINUM"),
    DIAMOND("DIAMOND"),
    RUBY("RUBY"),
    MASTER("MASTER"),
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
