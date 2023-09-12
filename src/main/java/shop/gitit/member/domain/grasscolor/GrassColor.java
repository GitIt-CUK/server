package shop.gitit.member.domain.grasscolor;

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

    private String color;

    GrassColor(String color) {
        this.color = color;
    }

    public static GrassColor findColor(String code) {
        return findColorByCode(code);
    }

    private static GrassColor findColorByCode(String color) {
        GrassColor grassColor = GrassColor.valueOf(color);
        if (grassColor == null) {
            throw new IllegalArgumentException();
        }
        return grassColor;
    }
}
