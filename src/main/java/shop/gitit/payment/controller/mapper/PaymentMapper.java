package shop.gitit.payment.controller.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import shop.gitit.payment.controller.response.GetPointRes;
import shop.gitit.payment.service.dto.GetPointDto;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class PaymentMapper {

    public static GetPointDto.Request toReqDto(long memberId) {
        return GetPointDto.Request.builder().memberId(memberId).build();
    }

    public static GetPointRes toRes(GetPointDto.Response response) {
        return GetPointRes.builder()
                .point(response.getPoint())
                .memberId(response.getMemberId())
                .build();
    }
}