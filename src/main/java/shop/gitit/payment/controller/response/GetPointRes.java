package shop.gitit.payment.controller.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetPointRes {

    private long memberId;
    private int point;
}
