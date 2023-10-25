package shop.gitit.member.service.usecase;

import shop.gitit.member.service.dto.response.LoginResDto;

public interface OauthUsecase {

    LoginResDto login(String provider, String code);
}
