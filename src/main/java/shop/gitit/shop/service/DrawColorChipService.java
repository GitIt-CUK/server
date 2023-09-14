package shop.gitit.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.github.domain.GitHubInfo;
import shop.gitit.github.exception.NoGitHubInfoException;
import shop.gitit.github.repository.GitHubInfoRepository;
import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.exception.NoWalletException;
import shop.gitit.payment.repository.PaymentRepository;
import shop.gitit.shop.domain.Shop;
import shop.gitit.shop.service.dto.request.DrawColorChipReqDto;
import shop.gitit.shop.service.dto.response.DrawColorChipResDto;
import shop.gitit.shop.service.usecase.DrawColorChipUsecase;

@Service
@RequiredArgsConstructor
@Transactional
public class DrawColorChipService implements DrawColorChipUsecase {

    private final PaymentRepository paymentRepository;
    private final GitHubInfoRepository gitHubInfoRepository;

    @Override
    public DrawColorChipResDto drawColorChip(DrawColorChipReqDto drawColorChipReqDto) {
        Shop shop = new Shop();
        // 아이템 타입의 가격만큼 포인트 조회 -> 상점 도메인
        int colorChipCost = shop.getPriceByCode(drawColorChipReqDto.getColorCode());
        // 포인트 조회 -> 결제 도메인
        Wallet wallet =
                paymentRepository
                        .findWalletByOwnerId(drawColorChipReqDto.getMemberId())
                        .orElseThrow(() -> new NoWalletException("지갑이 존재하지 않아요."));
        // 가격만큼 포인트 소모 -> 결제 도메인
        wallet.pay(colorChipCost);
        // 받은 색깔 코드로 잔디 색 변경 -> 깃허브 도메인
        GitHubInfo gitHubInfo =
                gitHubInfoRepository
                        .findGitHubInfoByMemberId(drawColorChipReqDto.getMemberId())
                        .orElseThrow(() -> new NoGitHubInfoException("깃허브 정보가 없어요."));
        gitHubInfo.changeGrassColor(drawColorChipReqDto.getColorCode());
        // 잔여 포인트 반환 -> 결제 도메인
        return DrawColorChipResDto.builder().remainingPoint(wallet.getPoint()).build();
    }
}
