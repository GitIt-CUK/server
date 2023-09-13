package shop.gitit.payment.service;

import org.springframework.stereotype.Service;
import shop.gitit.payment.service.dto.GetPointDto;
import shop.gitit.payment.service.usecase.GetPointUsecase;

@Service
public class GetPointService implements GetPointUsecase {

    public GetPointDto.Response getPoint(GetPointDto.Request getPointDto) {
        return null;
    }
}
