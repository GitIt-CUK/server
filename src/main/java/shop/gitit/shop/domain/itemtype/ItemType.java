package shop.gitit.shop.domain.itemtype;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum ItemType {
    COLOR_CHIP("COLOR-CHIP", 10),
    ;

    private final String type;
    private final int cost;

    ItemType(String type, int cost) {
        this.type = type;
        this.cost = cost;
    }

    public static ItemType findByCode(String code) {
        return Arrays.stream(ItemType.values())
                .filter(type -> code.equals(type.getType()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
