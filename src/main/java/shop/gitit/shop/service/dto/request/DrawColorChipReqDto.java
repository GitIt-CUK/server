package shop.gitit.shop.service.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DrawColorChipReqDto {

    private long memberId;
    private String itemType;
    private String colorCode;
}
