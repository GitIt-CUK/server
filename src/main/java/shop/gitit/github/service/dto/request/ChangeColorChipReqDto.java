package shop.gitit.github.service.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangeColorChipReqDto {

    private long memberId;
    private String colorCode;
}
