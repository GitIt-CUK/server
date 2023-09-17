package shop.gitit.shop.controller.mapper;

import shop.gitit.shop.controller.request.DrawColorChipReq;
import shop.gitit.shop.service.dto.request.DrawColorChipReqDto;

public class ShopMapper {

    public static DrawColorChipReqDto toDrawColorChipReqDto(
            DrawColorChipReq drawColorChipReq, long memberId) {
        return DrawColorChipReqDto.builder()
                .memberId(memberId)
                .itemType(drawColorChipReq.getItemType())
                .colorCode(drawColorChipReq.getColorCode())
                .build();
    }
}
