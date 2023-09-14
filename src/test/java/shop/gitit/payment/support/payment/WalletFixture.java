package shop.gitit.payment.support.payment;

import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.service.dto.request.GetPointReqDto;

public class WalletFixture {

    public static GetPointReqDto defaultGetPointDtoRequest() {
        long memberId = 1L;
        return GetPointReqDto.builder().memberId(memberId).build();
    }

    public static Wallet defaultWallet() {
        long memberId = 1L;
        GetPointReqDto request = GetPointReqDto.builder().memberId(memberId).build();
        return new Wallet(memberId);
    }
}
