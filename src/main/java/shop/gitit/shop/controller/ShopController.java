package shop.gitit.shop.controller;

import static shop.gitit.shop.controller.mapper.ShopMapper.toDrawColorChipReqDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.gitit.shop.controller.request.DrawColorChipReq;
import shop.gitit.shop.service.usecase.DrawColorChipUsecase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/shop")
public class ShopController {

    private final DrawColorChipUsecase drawColorChipUsecase;

    @PostMapping("/item/color-chip/{member-id}")
    public void drawColorChip(
            @RequestBody DrawColorChipReq drawColorChipReq,
            @PathVariable(name = "member-id") long memberId) {
        drawColorChipUsecase.drawColorChip(toDrawColorChipReqDto(drawColorChipReq, memberId));
    }
}
