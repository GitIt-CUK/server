package shop.gitit.payment.service.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetPointReqDto {

    private long memberId;
}
