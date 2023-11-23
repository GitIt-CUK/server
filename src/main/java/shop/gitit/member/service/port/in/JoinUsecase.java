package shop.gitit.member.service.port.in;

import shop.gitit.member.domain.Member;
import shop.gitit.member.service.dto.response.GithubUserInfo;

public interface JoinUsecase {

    Member join(GithubUserInfo githubUserInfo);
}
