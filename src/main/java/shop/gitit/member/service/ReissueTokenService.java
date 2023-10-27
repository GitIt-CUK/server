package shop.gitit.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gitit.core.util.jwt.JwtTokenProvider;
import shop.gitit.member.service.dto.ReissueTokenResDto;
import shop.gitit.member.service.port.in.ReissueTokenUsecase;

@Service
@RequiredArgsConstructor
public class ReissueTokenService implements ReissueTokenUsecase {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ReissueTokenResDto reissueToken(Long memberId, String refreshToken) {
        // 1. DB에서 refresh token 조회

        // 2. 클라이언트의 토큰과 일치하는지 검사

        // 3. 일치하지 않는다면 refresh token 삭제 & 로그아웃 처리 -> 예외를 던지자

        // 4. 일치한다면 ATK 생성 후 반환
        String accessToken = jwtTokenProvider.createAccessToken(memberId);
        return ReissueTokenResDto.builder().accessToken(accessToken).build();
    }
}
