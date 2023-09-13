package shop.gitit.payment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.exception.NoWalletException;
import shop.gitit.payment.repository.PaymentRepository;
import shop.gitit.payment.service.dto.GetPointDto;
import shop.gitit.payment.service.usecase.GetPointUsecase;
import shop.gitit.payment.support.payment.WalletFixture;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {GetPointService.class})
class GetPointServiceTest {

    @Autowired private GetPointUsecase getPointUsecase;

    @MockBean private PaymentRepository paymentRepository;

    @DisplayName("getPoint 메서드는")
    @Nested
    class GetPoint {

        @DisplayName("회원이 소유한 지갑이 존재한다면")
        @Nested
        class MemberHasWallet {

            @DisplayName("지갑 소유자의 id와 잔여 포인트를 반환한다.")
            @Test
            void success() {
                // given
                GetPointDto.Request request = WalletFixture.defaultGetPointDtoRequest();
                Wallet wallet = WalletFixture.defaultWallet();

                // when
                when(paymentRepository.findWalletByOwnerId(anyLong()))
                        .thenReturn(Optional.ofNullable(wallet));
                GetPointDto.Response result = getPointUsecase.getPoint(request);

                // then
                assertThat(result)
                        .extracting(
                                GetPointDto.Response::getMemberId, GetPointDto.Response::getPoint)
                        .contains(1L, 0);
            }
        }

        @DisplayName("회원이 소유한 지갑이 존재하지 않는다면")
        @Nested
        class MemberHasNoWallet {

            @DisplayName("예외를 던진다.")
            @Test
            void throwNoWalletException() {
                // given
                GetPointDto.Request request = WalletFixture.defaultGetPointDtoRequest();
                Wallet wallet = WalletFixture.defaultWallet();

                // when
                when(paymentRepository.findWalletByOwnerId(anyLong())).thenReturn(Optional.empty());

                // then
                assertThatThrownBy(() -> getPointUsecase.getPoint(request))
                        .isInstanceOf(NoWalletException.class);
            }
        }
    }
}
