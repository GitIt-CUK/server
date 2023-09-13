package shop.gitit.github.domain.tier;

import java.util.stream.Stream;

public enum Tier {
    IRON(0),
    BRONZE4(2),
    BRONZE3(4),
    BRONZE2(6),
    BRONZE1(8),
    SILVER4(20),
    SILVER3(32),
    SILVER2(44),
    SILVER1(56),
    GOLD4(71),
    GOLD3(86),
    GOLD2(101),
    GOLD1(116),
    PLATINUM4(136),
    PLATINUM3(156),
    PLATINUM2(176),
    PLATINUM1(196),
    DIAMOND4(246),
    DIAMOND3(296),
    DIAMOND2(346),
    DIAMOND1(396),
    GOAT(1234567890),
    ;

    private final int limit;

    Tier(int limit) {
        this.limit = limit;
    }

    public static Tier updateTier(int commitCount) {
        return Stream.of(values())
                .filter(tier -> tier.limit <= commitCount)
                .reduce(((first, second) -> second))
                .orElse(GOAT);
    }

    public static Tier init() {
        return IRON;
    }
}
