package shop.gitit.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.github.domain.GitHubInfo;
import shop.gitit.github.exception.NoGitHubInfoException;
import shop.gitit.github.repository.GitHubInfoRepository;
import shop.gitit.shop.domain.Shop;
import shop.gitit.shop.service.dto.request.DrawColorChipReqDto;
import shop.gitit.shop.service.event.PaymentEvent;
import shop.gitit.shop.service.usecase.DrawColorChipUsecase;

@Service
@RequiredArgsConstructor
@Transactional
public class DrawColorChipService implements DrawColorChipUsecase {

    private final GitHubInfoRepository gitHubInfoRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void drawColorChip(DrawColorChipReqDto req) {
        publishPayEvent(req);
        GitHubInfo gitHubInfo =
                gitHubInfoRepository
                        .findGitHubInfoByMemberId(req.getMemberId())
                        .orElseThrow(() -> new NoGitHubInfoException("깃허브 정보가 없어요."));
        gitHubInfo.changeGrassColor(req.getColorCode());
    }

    private void publishPayEvent(DrawColorChipReqDto req) {
        eventPublisher.publishEvent(
                PaymentEvent.builder()
                        .payerId(req.getMemberId())
                        .cost(Shop.getItemPriceByType(req.getItemType()))
                        .build());
    }
}
