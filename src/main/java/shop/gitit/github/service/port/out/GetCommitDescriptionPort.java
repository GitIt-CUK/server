package shop.gitit.github.service.port.out;

import shop.gitit.github.service.dto.response.GetCommitsDescriptionResDto;

public interface GetCommitDescriptionPort {

    GetCommitsDescriptionResDto getCommitDescription(String githubId);
}
