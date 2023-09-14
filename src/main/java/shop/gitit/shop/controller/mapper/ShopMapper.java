package shop.gitit.shop.controller.mapper;

import shop.gitit.shop.service.dto.request.DrawColorChipReqDto;

public class ShopMapper {

    public static DrawColorChipReqDto toDrawColorChipReqDto(long memberId) {
        return DrawColorChipReqDto.builder().memberId(memberId).build();
    }
}
