package shop.gitit.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.core.util.jwt.JwtTokenProvider;
import shop.gitit.core.util.jwt.dto.JwtToken;
import shop.gitit.member.domain.Member;
import shop.gitit.member.domain.RefreshToken;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.repository.RedisRepository;
import shop.gitit.member.service.dto.response.GithubUserInfo;
import shop.gitit.member.service.dto.response.LoginResDto;
import shop.gitit.member.service.dto.response.OAuthTokenResponse;
import shop.gitit.member.service.port.in.LoginUsecase;
import shop.gitit.member.service.port.out.OAuthWebClient;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LoginService implements LoginUsecase {

    private final JoinService joinService;
    private final JwtTokenProvider jwtTokenProvider;
    private final OAuthWebClient oAuthWebClient;
    private final MemberRepository memberRepository;
    private final RedisRepository redisRepository;

    @Override
    public LoginResDto login(String code) {
        OAuthTokenResponse tokenResponse = oAuthWebClient.getToken(code);
        GithubUserInfo githubUserInfo = oAuthWebClient.getUserProfile(tokenResponse);
        Member member = memberRepository.findByGithubId(githubUserInfo.getGithubId()).orElse(null);
        member = joinIfNewMember(githubUserInfo, member);
        updateProfileImg(githubUserInfo, member);
        JwtToken jwtToken = jwtTokenProvider.createToken(member.getId());
        redisRepository.save(
                RefreshToken
                        .builder() // TODO redis에 저장까지 완료한 후, 이후 로직에서 에러가 발생하여 트랜잭션 롤백이 발생한다면 RTK는
                        // 삭제되어야 한다.
                        .memberId(member.getId())
                        .token(jwtToken.getRefreshToken())
                        .build());
        return LoginResDto.builder()
                .memberId(member.getId())
                .refreshToken(jwtToken.getRefreshToken())
                .accessToken(jwtToken.getAccessToken())
                .build();
    }

    private Member joinIfNewMember(GithubUserInfo githubUserInfo, Member member) {
        if (member == null) {
            member = joinService.join(githubUserInfo);
        }
        return member;
    }

    private void updateProfileImg(GithubUserInfo githubUserInfo, Member member) {
        if (!member.getProfile().getProfileImg().equals(githubUserInfo.getProfileImg())) {
            member.getProfile().updateProfileImg(githubUserInfo.getProfileImg());
        }
    }
}
