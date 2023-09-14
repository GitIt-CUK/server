package shop.gitit.payment.controller.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetPointRes {

    private String message;
    private long memberId;
    private int point;
}
