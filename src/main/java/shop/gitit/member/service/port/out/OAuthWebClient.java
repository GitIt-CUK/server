package shop.gitit.member.service.port.out;

import shop.gitit.member.service.dto.response.GithubUserInfo;
import shop.gitit.member.service.dto.response.OAuthTokenResponse;

public interface OAuthWebClient {

    OAuthTokenResponse getToken(String code);

    GithubUserInfo getUserProfile(OAuthTokenResponse tokenResponse);
}
