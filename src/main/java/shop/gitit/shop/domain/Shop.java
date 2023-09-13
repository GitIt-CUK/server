package shop.gitit.shop.domain;

import shop.gitit.shop.domain.itemtype.ItemType;

public class Shop {

    public int getPriceByCode(String code) {
        ItemType item = ItemType.findByCode(code);
        return item.getCost();
    }
}
