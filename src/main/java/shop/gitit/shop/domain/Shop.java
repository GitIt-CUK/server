package shop.gitit.shop.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import shop.gitit.shop.domain.itemtype.ItemType;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Shop {

    public static int getItemPriceByType(String code) {
        ItemType item = ItemType.findByCode(code);
        return item.getCost();
    }
}
