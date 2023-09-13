package shop.gitit.payment.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.gitit.payment.domain.Wallet;

public interface PaymentRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findWalletByOwnerId(long memberId);
}
