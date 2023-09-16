package shop.gitit.shop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import shop.gitit.github.domain.GitHubInfo;
import shop.gitit.github.domain.grasscolor.GrassColor;
import shop.gitit.github.repository.GitHubInfoRepository;
import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.exception.PointViolationException;
import shop.gitit.shop.domain.service.PaymentCompletionChecker;
import shop.gitit.shop.service.dto.request.DrawColorChipReqDto;
import shop.gitit.shop.service.usecase.DrawColorChipUsecase;
import shop.gitit.shop.support.ServiceDtoFixture;

@SpringBootTest
class DrawColorChipServiceTest {

    @Autowired private ApplicationEventPublisher eventPublisher;
    @Autowired private DrawColorChipUsecase drawColorChipUsecase;
    @MockBean private GitHubInfoRepository gitHubInfoRepository;
    @MockBean private PaymentCompletionChecker paymentCompletionChecker;

    @DisplayName("drawColorChip 메서드는")
    @Nested
    class drawColorChip {

        @DisplayName("아이템 종류를 파악한 후 가격만큼 결제 요청을 하는데,")
        @Nested
        class findItemTypeAndRequestForPayments {

            @DisplayName("유저의 잔액이 부족하면")
            @Nested
            class userAccountIsOverdrawn {

                @DisplayName("결제에 실패하고 예외를 던진다.")
                @Test
                void throwException() {
                    // given
                    long memberId = 1L;
                    DrawColorChipReqDto req = ServiceDtoFixture.defaultDrawColorChipReqDto();
                    Wallet wallet = new Wallet(memberId);

                    // when
                    when(paymentCompletionChecker.completePayment(anyLong(), anyInt()))
                            .thenThrow(PointViolationException.class);

                    // then
                    assertThatThrownBy(() -> drawColorChipUsecase.drawColorChip(req))
                            .isInstanceOf(PointViolationException.class);
                }
            }

            @DisplayName("유저의 잔액이 충분하면")
            @Nested
            class userAccountIsNotOverdrawn {

                @DisplayName("결제에 성공하고 컬러칩 변경 이벤트를 호출하고 컬러칩이 변경된다.")
                @Test
                void payAndChangeColorChip() {
                    // given
                    long memberId = 1L;
                    DrawColorChipReqDto req = ServiceDtoFixture.defaultDrawColorChipReqDto();
                    Wallet wallet = new Wallet(memberId);
                    wallet.accumulatePoint(100);
                    GitHubInfo gitHubInfo = new GitHubInfo(memberId);

                    // when
                    when(paymentCompletionChecker.completePayment(anyLong(), anyInt()))
                            .thenReturn(true);
                    when(gitHubInfoRepository.findGitHubInfoByMemberId(anyLong()))
                            .thenReturn(Optional.ofNullable(gitHubInfo));
                    drawColorChipUsecase.drawColorChip(req);

                    // then
                    assertThat(gitHubInfo.getGrassColor()).isEqualTo(GrassColor.RARE);
                }
            }
        }
    }
}
