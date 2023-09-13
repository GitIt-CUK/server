package shop.gitit.payment.repository;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.gitit.payment.domain.Wallet;

@DataJpaTest
class PaymentRepositoryTest {

    @Autowired private PaymentRepository paymentRepository;
    @Autowired private EntityManager em;

    @DisplayName("회원이 소유한 지갑을 찾는다.")
    @Test
    void findWallet() {
        // given
        long memberId = 1L;
        Wallet wallet = new Wallet(memberId);

        // when
        paymentRepository.save(wallet);
        em.flush();
        Wallet result = paymentRepository.findWalletByOwnerId(memberId).get();

        // then
        assertThat(result).extracting(Wallet::getPoint, Wallet::getOwnerId).contains(0, 1L);
    }
}
