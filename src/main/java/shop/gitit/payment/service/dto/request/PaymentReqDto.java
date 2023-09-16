package shop.gitit.payment.service.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentReqDto {

    private long payerId;
    private int cost;
}
