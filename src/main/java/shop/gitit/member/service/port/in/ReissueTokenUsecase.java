package shop.gitit.member.service.port.in;

import shop.gitit.member.service.dto.ReissueTokenResDto;

public interface ReissueTokenUsecase {

    ReissueTokenResDto reissueToken(Long memberId, String refreshToken);
}
