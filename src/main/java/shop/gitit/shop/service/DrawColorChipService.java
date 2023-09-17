package shop.gitit.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.shop.domain.Shop;
import shop.gitit.shop.service.dto.request.DrawColorChipReqDto;
import shop.gitit.shop.service.event.ChangedColorChipEvent;
import shop.gitit.shop.service.event.DrewColorChipEvent;
import shop.gitit.shop.service.usecase.DrawColorChipUsecase;

@Service
@RequiredArgsConstructor
@Transactional
public class DrawColorChipService implements DrawColorChipUsecase {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void drawColorChip(DrawColorChipReqDto req) {
        eventPublisher.publishEvent(createDrewColorChipEvent(req));
        eventPublisher.publishEvent(createChangedColorChipEvent(req));
    }

    private DrewColorChipEvent createDrewColorChipEvent(DrawColorChipReqDto req) {
        return DrewColorChipEvent.builder()
                .memberId(req.getMemberId())
                .cost(getItemPrice(req.getItemType()))
                .build();
    }

    private ChangedColorChipEvent createChangedColorChipEvent(DrawColorChipReqDto req) {
        return ChangedColorChipEvent.builder()
                .memberId(req.getMemberId())
                .colorCode(req.getColorCode())
                .build();
    }

    private int getItemPrice(String type) {
        return Shop.getItemPriceByType(type);
    }
}
