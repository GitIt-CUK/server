package shop.gitit.github.service.usecase;

import shop.gitit.github.service.dto.response.GetCommitsDescriptionResDto;

public interface CommitsDescriptionUsecase {
    GetCommitsDescriptionResDto getCommitsDescription(Long memberId);
}
