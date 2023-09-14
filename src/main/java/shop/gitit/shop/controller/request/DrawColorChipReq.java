package shop.gitit.shop.controller.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DrawColorChipReq {

    private String itemType;
    private String colorCode;
}
