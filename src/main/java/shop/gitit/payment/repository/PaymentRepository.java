package shop.gitit.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.domain.memberId.MemberId;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByOwnerId(MemberId memberId);
}
