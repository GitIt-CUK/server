package shop.gitit.payment.controller.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import shop.gitit.payment.service.dto.request.GetPointReqDto;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class PaymentMapper {

    public static GetPointReqDto toReqDto(long memberId) {
        return GetPointReqDto.builder().memberId(memberId).build();
    }
}
