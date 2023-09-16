package shop.gitit.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.exception.NoWalletException;
import shop.gitit.payment.repository.PaymentRepository;
import shop.gitit.payment.service.dto.request.PaymentReqDto;
import shop.gitit.payment.service.usecase.PaymentUsecase;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService implements PaymentUsecase {

    private final PaymentRepository paymentRepository;

    @Override
    public void pay(PaymentReqDto req) {
        Wallet wallet =
                paymentRepository
                        .findWalletByOwnerId(req.getPayerId())
                        .orElseThrow(() -> new NoWalletException("계좌 정보가 없습니다."));
        wallet.pay(req.getCost());
    }
}
