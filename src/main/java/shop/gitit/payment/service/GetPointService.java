package shop.gitit.payment.service;

import static shop.gitit.payment.service.helper.PaymentServiceHelper.findWallet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.repository.PaymentRepository;
import shop.gitit.payment.service.dto.request.GetPointReqDto;
import shop.gitit.payment.service.dto.response.GetPointResDto;
import shop.gitit.payment.service.usecase.GetPointUsecase;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetPointService implements GetPointUsecase {

    private final PaymentRepository paymentRepository;

    public GetPointResDto getPoint(GetPointReqDto getPointDto) {
        Wallet wallet = findWallet(paymentRepository, getPointDto.getMemberId());
        return GetPointResDto.builder()
                .memberId(wallet.getOwnerId())
                .point(wallet.getPoint())
                .build();
    }
}
