package shop.gitit.github.service;

import static shop.gitit.github.domain.exchanger.Exchanger.exchangeToPoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.github.domain.GitHubInfo;
import shop.gitit.github.exception.NoGitHubInfoException;
import shop.gitit.github.repository.GitHubInfoRepository;
import shop.gitit.github.service.dto.AddPointResDto;
import shop.gitit.github.service.port.in.AddPointUsecase;
import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.exception.NoWalletException;
import shop.gitit.payment.repository.PaymentRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AddPointService implements AddPointUsecase {

    private final GitHubInfoRepository gitHubInfoRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public AddPointResDto addPoint(long memberId, int commitCount) {
        GitHubInfo gitHubInfo =
                gitHubInfoRepository
                        .findGitHubInfoByMemberId(memberId)
                        .orElseThrow(NoGitHubInfoException::new);
        gitHubInfo.updateCommitCount(commitCount);
        Wallet wallet =
                paymentRepository.findWalletByOwnerId(memberId).orElseThrow(NoWalletException::new);
        wallet.accumulatePoint(exchangeToPoint(commitCount));
        return AddPointResDto.builder().message("포인트 적립 완료되었습니다.").build();
    }
}
