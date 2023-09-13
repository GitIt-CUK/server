package shop.gitit.payment.controller;

import static shop.gitit.payment.controller.mapper.PaymentMapper.toReqDto;
import static shop.gitit.payment.controller.mapper.PaymentMapper.toRes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.gitit.payment.controller.response.GetPointRes;
import shop.gitit.payment.service.dto.GetPointDto;
import shop.gitit.payment.service.usecase.GetPointUsecase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pay")
public class PaymentController {

    private final GetPointUsecase getPointUsecase;

    @GetMapping("/point/{member-id}")
    public ResponseEntity<GetPointRes> getPoint(@PathVariable(name = "member-id") long memberId) {
        GetPointDto.Response response = getPointUsecase.getPoint(toReqDto(memberId));
        return ResponseEntity.ok(toRes(response));
    }
}
