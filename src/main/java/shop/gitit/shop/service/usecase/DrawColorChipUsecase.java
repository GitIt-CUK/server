package shop.gitit.shop.service.usecase;

import shop.gitit.shop.service.dto.request.DrawColorChipReqDto;
import shop.gitit.shop.service.dto.response.DrawColorChipResDto;

public interface DrawColorChipUsecase {

    DrawColorChipResDto drawColorChip(DrawColorChipReqDto drawColorChipReqDto);
}
