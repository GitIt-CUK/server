package shop.gitit.payment.service;

import static shop.gitit.payment.service.helper.PaymentServiceHelper.findWallet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.repository.PaymentRepository;
import shop.gitit.payment.service.dto.PayReqDto;
import shop.gitit.payment.service.usecase.PaymentUsecase;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUsecase {

    private PaymentRepository paymentRepository;

    @Override
    public void pay(PayReqDto req) {
        Wallet wallet = findWallet(paymentRepository, req.getPayerId());
        wallet.pay(req.getCost());
    }
}
