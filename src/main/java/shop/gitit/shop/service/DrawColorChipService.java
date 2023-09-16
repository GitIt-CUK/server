package shop.gitit.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.shop.domain.Shop;
import shop.gitit.shop.domain.service.PaymentCompletionChecker;
import shop.gitit.shop.service.dto.request.DrawColorChipReqDto;
import shop.gitit.shop.service.event.ChangedColorChipEvent;
import shop.gitit.shop.service.usecase.DrawColorChipUsecase;

@Service
@RequiredArgsConstructor
@Transactional
public class DrawColorChipService implements DrawColorChipUsecase {

    private final PaymentCompletionChecker paymentCompletionChecker;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void drawColorChip(DrawColorChipReqDto req) {
        if (paymentCompletionChecker.completePayment(
                req.getMemberId(), getItemPrice(req.getItemType()))) {
            eventPublisher.publishEvent(
                    ChangedColorChipEvent.builder()
                            .memberId(req.getMemberId())
                            .colorCode(req.getColorCode())
                            .build());
        }
    }

    private int getItemPrice(String itemType) {
        return Shop.getItemPriceByType(itemType);
    }
}
