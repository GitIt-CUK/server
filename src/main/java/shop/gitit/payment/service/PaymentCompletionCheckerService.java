package shop.gitit.payment.service;

import static shop.gitit.payment.service.helper.PaymentServiceHelper.findWallet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.exception.PointViolationException;
import shop.gitit.payment.repository.PaymentRepository;
import shop.gitit.shop.domain.service.PaymentCompletionChecker;

@Service
@RequiredArgsConstructor
public final class PaymentCompletionCheckerService implements PaymentCompletionChecker {

    private PaymentRepository paymentRepository;

    @Override
    public boolean completePayment(long memberId, int cost) {
        Wallet wallet = findWallet(paymentRepository, memberId);
        try {
            wallet.pay(cost);
            return true;
        } catch (PointViolationException e) {
            throw new PointViolationException("결제에 실패했습니다.", e);
        }
    }
}
