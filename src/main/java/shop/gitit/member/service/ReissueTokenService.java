package shop.gitit.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shop.gitit.core.util.jwt.JwtTokenProvider;
import shop.gitit.member.domain.RefreshToken;
import shop.gitit.member.exception.NoRefreshTokenException;
import shop.gitit.member.exception.StolenTokenException;
import shop.gitit.member.repository.RedisRepository;
import shop.gitit.member.service.dto.ReissueTokenResDto;
import shop.gitit.member.service.port.in.ReissueTokenUsecase;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReissueTokenService implements ReissueTokenUsecase {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisRepository redisRepository;

    @Override
    public ReissueTokenResDto reissueToken(Long memberId, String refreshToken) {
        log.info("회원 {}이 RTK {}를 사용하여 ATK 재발급 요청", memberId, refreshToken);
        RefreshToken token =
                redisRepository
                        .findById(memberId)
                        .orElseThrow(
                                () ->
                                        new NoRefreshTokenException(
                                                "Refresh Token이 더이상 유효하지 않습니다. 다시 로그인 하세요."));
        if (!token.getToken().equals(refreshToken)) { // TODO: RTK가 일치하지 않는 것일 뿐이다.
            log.warn("회원 {}의 RTK {}이 탈취당했습니다.", memberId, refreshToken);
            redisRepository.deleteById(memberId);
            throw new StolenTokenException("토큰이 탈취당했습니다.");
        }
        redisRepository.save(RefreshToken.builder().memberId(memberId).token(refreshToken).build());
        String accessToken = jwtTokenProvider.createAccessToken(memberId);
        return ReissueTokenResDto.builder().accessToken(accessToken).build();
    }
}
