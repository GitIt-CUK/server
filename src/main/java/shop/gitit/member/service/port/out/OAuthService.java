package shop.gitit.member.service.port.out;

import shop.gitit.member.service.dto.response.OAuthTokenResponse;
import shop.gitit.member.service.dto.response.UserProfileResDto;

public interface OAuthService {

    OAuthTokenResponse getToken(String code);

    UserProfileResDto getUserProfile(OAuthTokenResponse tokenResponse);
}