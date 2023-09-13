package shop.gitit.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.domain.memberId.MemberId;
import shop.gitit.payment.exception.NoWalletException;
import shop.gitit.payment.repository.PaymentRepository;
import shop.gitit.payment.service.dto.GetPointDto;
import shop.gitit.payment.service.usecase.GetPointUsecase;

@Service
@RequiredArgsConstructor
public class GetPointService implements GetPointUsecase {

    private final PaymentRepository paymentRepository;

    public GetPointDto.Response getPoint(GetPointDto.Request getPointDto) {
        Wallet wallet = paymentRepository.findByOwnerId(new MemberId(getPointDto.getMemberId()))
                .orElseThrow(() -> new NoWalletException("해당 회원의 포인트가 존재하지 않습니다."));
        return GetPointDto.Response.builder()
                .memberId(wallet.getOwnerId())
                .point(wallet.getPoint())
                .build();
    }
}
