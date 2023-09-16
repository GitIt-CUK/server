package shop.gitit.core.event.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import shop.gitit.payment.service.dto.request.PaymentReqDto;
import shop.gitit.payment.service.usecase.PaymentUsecase;
import shop.gitit.shop.service.event.PaymentEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentHandler {

    private final PaymentUsecase paymentUsecase;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT, value = PaymentEvent.class)
    public void pay(PaymentEvent event) {
        log.info(
                "[PAYMENT-EVENT] payerId: {}, cost: {}, 결제 요청",
                event.getPayerId(),
                event.getCost());
        paymentUsecase.pay(
                PaymentReqDto.builder().payerId(event.getPayerId()).cost(event.getCost()).build());
    }
}
