package shop.gitit.payment.service.usecase;

import shop.gitit.payment.service.dto.request.GetPointReqDto;
import shop.gitit.payment.service.dto.response.GetPointResDto;

public interface GetPointUsecase {

    GetPointResDto getPoint(GetPointReqDto getPointReqDto);
}
