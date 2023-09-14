package shop.gitit.shop.support;

import shop.gitit.shop.service.dto.request.DrawColorChipReqDto;

public class ServiceDtoFixture {

    public static DrawColorChipReqDto defaultDrawColorChipReqDto() {
        return DrawColorChipReqDto.builder()
                .itemType("COLOR-CHIP")
                .colorCode("RARE")
                .memberId(1L)
                .build();
    }
}
