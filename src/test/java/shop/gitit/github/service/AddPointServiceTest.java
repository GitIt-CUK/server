package shop.gitit.github.service;

import static org.assertj.core.api.Assertions.assertThat;
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
import shop.gitit.github.domain.GitHubInfo;
import shop.gitit.github.repository.GitHubInfoRepository;
import shop.gitit.github.service.port.in.AddPointUsecase;
import shop.gitit.github.support.githubinfo.GitHubInfoFixture;
import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.repository.PaymentRepository;
import shop.gitit.payment.support.payment.WalletFixture;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AddPointService.class})
class AddPointServiceTest {

    @Autowired private AddPointUsecase addPointUsecase;
    @MockBean private GitHubInfoRepository gitHubInfoRepository;
    @MockBean private PaymentRepository paymentRepository;

    @Nested
    @DisplayName("특정 회원의 커밋 내역을 전달받을 때")
    class WhenPostMemberIdAndCommitCount {

        @Nested
        @DisplayName("해당 회원의 깃허브 정보가 존재하고")
        class HaveGitHubInfo {

            @Nested
            @DisplayName("지갑 정보도 존재한다면")
            class HaveWallet {

                GitHubInfo gitHubInfo = GitHubInfoFixture.getGitHubInfo(1L);
                Wallet wallet = WalletFixture.defaultWallet();

                @DisplayName("새롭게 추가된 커밋 수가 더해진다.")
                @Test
                void addCommitCountSuccess() {
                    // given

                    // when
                    when(gitHubInfoRepository.findGitHubInfoByMemberId(anyLong()))
                            .thenReturn(Optional.ofNullable(gitHubInfo));
                    when(paymentRepository.findWalletByOwnerId(anyLong()))
                            .thenReturn(Optional.ofNullable(wallet));
                    addPointUsecase.addPoint(1L, 10);

                    // then
                    assertThat(gitHubInfo.getCommitCount().getCount()).isEqualTo(10);
                    assertThat(wallet.getPoint()).isEqualTo(10 * 20);
                }
            }
        }
    }
}
