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
import shop.gitit.member.service.dto.response.OAuthTokenResponse;
import shop.gitit.member.service.dto.response.UserProfileResDto;
import shop.gitit.member.service.port.out.OAuthUsecase;

@Slf4j
@Service
public class OAuthService implements OAuthUsecase {

    private final String redirectUri;
    private final String tokenUri;
    private final String userInfoUri;
    private final String clientId;
    private final String clientSecret;

    public OAuthService(
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
                .orElseThrow(() -> new RuntimeException()); // TODO 커스텀 예외 추가
    }

    private MultiValueMap<String, String> makeTokenRequestBody(String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", redirectUri);
        return formData;
    }

    @Override
    public UserProfileResDto getUserProfile(OAuthTokenResponse tokenResponse) {
        Map<String, Object> userAttributes = getUserAttributes(tokenResponse);
        log.info(
                "==== 깃허브 회원정보 조회 ====\nlogin={}, name={}, email={}, avatar_url={}",
                userAttributes.get("login"),
                userAttributes.get("name"),
                userAttributes.get("email"),
                userAttributes.get("avatar_url"));
        return UserProfileResDto.builder() // TODO 다른 소셜 로그인이 추가될 때 변경에 용이하도록 개선하기
                .githubId((String) userAttributes.get("login"))
                .name((String) userAttributes.get("name"))
                .email((String) userAttributes.get("email"))
                .imageUrl((String) userAttributes.get("avatar_url"))
                .build();
    }

    private Map<String, Object> getUserAttributes(OAuthTokenResponse tokenResponse) {
        return WebClient.create()
                .get()
                .uri(userInfoUri)
                .headers(header -> header.setBearerAuth(tokenResponse.getAccessToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .blockOptional()
                .orElseThrow(() -> new RuntimeException()); // TODO 커스텀 예외 추가
    }
}
