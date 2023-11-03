package shop.gitit.member.service.port.in;

import shop.gitit.member.service.dto.response.LoginResDto;

public interface LoginUsecase {

    LoginResDto login(String code);
}
