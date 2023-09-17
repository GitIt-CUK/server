package shop.gitit.shop.service.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangedColorChipEvent {

    private long memberId;
    private String colorCode;
}
