package shop.gitit.infra.service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import shop.gitit.infra.exception.FailGetGitHubAccessTokenException;
import shop.gitit.infra.exception.FailGetGitHubUserInfoException;
import shop.gitit.member.service.dto.response.GithubUserInfo;
import shop.gitit.member.service.dto.response.OAuthTokenResponse;
import shop.gitit.member.service.port.out.OAuthWebClient;

@Slf4j
@Service
public class OAuthWebClientImpl implements OAuthWebClient {

    private final String redirectUri;
    private final String tokenUri;
    private final String userInfoUri;
    private final String clientId;
    private final String clientSecret;

    public OAuthWebClientImpl(
            @Value("${spring.security.oauth2.client.registration.github.redirect-uri}")
                    String redirectUri,
            @Value("${spring.security.oauth2.client.provider.github.token-uri}") String tokenUri,
            @Value("${spring.security.oauth2.client.provider.github.user-info-uri}")
                    String userInfoUri,
            @Value("${spring.security.oauth2.client.registration.github.client-id}")
                    String clientId,
            @Value("${spring.security.oauth2.client.registration.github.client-secret}")
                    String clientSecret) {
        this.redirectUri = redirectUri;
        this.tokenUri = tokenUri;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.userInfoUri = userInfoUri;
    }

    @Override
    public OAuthTokenResponse getToken(String code) {
        return WebClient.create()
                .post()
                .uri(tokenUri)
                .headers(
                        header -> {
                            header.setBasicAuth(clientId, clientSecret);
                            header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                            header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                            header.setAcceptCharset(
                                    Collections.singletonList(StandardCharsets.UTF_8));
                        })
                .bodyValue(makeTokenRequestBody(code))
                .retrieve()
                .bodyToMono(OAuthTokenResponse.class)
                .blockOptional()
                .orElseThrow(FailGetGitHubAccessTokenException::new);
    }

    private MultiValueMap<String, String> makeTokenRequestBody(String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", redirectUri);
        return formData;
    }

    @Override
    public GithubUserInfo getUserProfile(OAuthTokenResponse tokenResponse) {
        Map<String, Object> gitHubUserInfo = getGitHubUserInfo(tokenResponse);
        log.info(
                "==== 깃허브 회원정보 조회 ====\nlogin={}, name={}, email={}, avatar_url={}",
                gitHubUserInfo.get("login"),
                gitHubUserInfo.get("name"),
                gitHubUserInfo.get("email"),
                gitHubUserInfo.get("avatar_url"));
        return GithubUserInfo.builder()
                .githubId((String) gitHubUserInfo.get("login"))
                .nickname((String) gitHubUserInfo.get("name"))
                .email((String) gitHubUserInfo.get("email"))
                .profileImg((String) gitHubUserInfo.get("avatar_url"))
                .build();
    }

    private Map<String, Object> getGitHubUserInfo(OAuthTokenResponse tokenResponse) {
        return WebClient.create()
                .get()
                .uri(userInfoUri)
                .headers(header -> header.setBearerAuth(tokenResponse.getAccessToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .blockOptional()
                .orElseThrow(FailGetGitHubUserInfoException::new);
    }
}
