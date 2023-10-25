package shop.gitit.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.service.dto.response.LoginResDto;
import shop.gitit.member.service.dto.response.OAuthTokenResponse;
import shop.gitit.member.service.dto.response.UserProfileResDto;
import shop.gitit.member.service.port.in.LoginUsecase;
import shop.gitit.member.service.port.out.OAuthUsecase;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LoginService implements LoginUsecase {

    private final OAuthUsecase oAuthUsecase;
    private final MemberRepository memberRepository;

    @Override
    public LoginResDto login(String code) {
        OAuthTokenResponse tokenResponse = oAuthUsecase.getToken(code);
        UserProfileResDto userProfileResDto = oAuthUsecase.getUserProfile(tokenResponse);
        // TODO 이미 가입된 회원 -> 로그인, 가입 정보 없음 -> 회원가입
        return LoginResDto.builder()
                .email(userProfileResDto.getEmail())
                .githubId(userProfileResDto.getGithubId())
                .name(userProfileResDto.getName())
                .imageUrl(userProfileResDto.getImageUrl())
                .build();
    }
}
