package shop.gitit.payment.support.payment;

import shop.gitit.payment.domain.Wallet;
import shop.gitit.payment.service.dto.GetPointDto;

public class WalletFixture {

    public static GetPointDto.Request defaultGetPointDtoRequest() {
        long memberId = 1L;
        return GetPointDto.Request.builder().memberId(memberId).build();
    }

    public static Wallet defaultWallet() {
        long memberId = 1L;
        GetPointDto.Request request = GetPointDto.Request.builder().memberId(memberId).build();
        return new Wallet(memberId);
    }
}
