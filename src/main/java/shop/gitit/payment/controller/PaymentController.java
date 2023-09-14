package shop.gitit.payment.controller;

import static shop.gitit.payment.controller.mapper.PaymentMapper.toReqDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.gitit.payment.service.dto.response.GetPointResDto;
import shop.gitit.payment.service.usecase.GetPointUsecase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pay")
public class PaymentController {

    private final GetPointUsecase getPointUsecase;

    @GetMapping("/point/{member-id}")
    public ResponseEntity<GetPointResDto> getPoint(
            @PathVariable(name = "member-id") long memberId) {
        return ResponseEntity.ok(getPointUsecase.getPoint(toReqDto(memberId)));
    }
}
