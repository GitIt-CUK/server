package shop.gitit.core.event.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import shop.gitit.payment.service.dto.PayReqDto;
import shop.gitit.payment.service.usecase.PaymentUsecase;
import shop.gitit.shop.service.event.DrewColorChipEvent;

@Component
@RequiredArgsConstructor
public class DrewColorChipHandler {

    private final PaymentUsecase paymentUsecase;

    @EventListener(DrewColorChipEvent.class)
    public void payForDrewColorChip(DrewColorChipEvent event) {
        paymentUsecase.pay(
                PayReqDto.builder().payerId(event.getMemberId()).cost(event.getCost()).build());
    }
}
