package shop.gitit.payment.service.usecase;

import shop.gitit.payment.service.dto.request.PaymentReqDto;

public interface PaymentUsecase {

    void pay(PaymentReqDto req);
}
