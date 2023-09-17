package shop.gitit.shop.service.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DrewColorChipEvent {

    private long memberId;
    private int cost;
}
