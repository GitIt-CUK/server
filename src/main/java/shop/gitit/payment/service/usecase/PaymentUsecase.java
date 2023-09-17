package shop.gitit.payment.service.usecase;

import shop.gitit.payment.service.dto.PayReqDto;

public interface PaymentUsecase {

    void pay(PayReqDto req);
}
