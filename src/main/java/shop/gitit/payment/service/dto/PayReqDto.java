package shop.gitit.payment.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PayReqDto {

    private long payerId;
    private int cost;
}
