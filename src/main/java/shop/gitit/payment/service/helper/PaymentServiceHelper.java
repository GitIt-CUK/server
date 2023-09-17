package shop.gitit.payment.service.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.exception.NoWalletException;
import shop.gitit.payment.repository.PaymentRepository;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class PaymentServiceHelper {

    public static Wallet findWallet(PaymentRepository paymentRepository, long memberId) {
        return paymentRepository
                .findWalletByOwnerId(memberId)
                .orElseThrow(() -> new NoWalletException("지갑이 존재하지 않습니다."));
    }
}
