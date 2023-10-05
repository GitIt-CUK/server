package shop.gitit.github.service.port.in;

import shop.gitit.github.service.dto.response.GetCommitsDescriptionResDto;

public interface GetCommitsDescriptionUsecase {
    GetCommitsDescriptionResDto getCommitsDescription(Long memberId);
}
