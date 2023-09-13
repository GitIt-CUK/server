package shop.gitit.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gitit.payment.domain.Wallet;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findWalletByOwnerId(long memberId);
}
