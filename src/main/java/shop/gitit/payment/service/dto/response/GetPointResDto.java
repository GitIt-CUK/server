package shop.gitit.payment.service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetPointResDto {

    private long memberId;
    private int point;
}
