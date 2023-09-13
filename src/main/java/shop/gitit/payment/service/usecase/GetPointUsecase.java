package shop.gitit.payment.service.usecase;

import shop.gitit.payment.service.dto.GetPointDto;

public interface GetPointUsecase {

    GetPointDto.Response getPoint(GetPointDto.Request getPointDto);
}
