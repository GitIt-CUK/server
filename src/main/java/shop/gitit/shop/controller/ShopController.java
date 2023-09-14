package shop.gitit.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.gitit.shop.controller.mapper.ShopMapper;
import shop.gitit.shop.service.dto.response.DrawColorChipResDto;
import shop.gitit.shop.service.usecase.DrawColorChipUsecase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/shop")
public class ShopController {

    private final DrawColorChipUsecase drawColorChipUsecase;

    @PostMapping("/item/color-chip/{member-id}")
    public ResponseEntity<DrawColorChipResDto> drawColorChip(
            @PathVariable(name = "member-id") long memberId) {
        return ResponseEntity.ok(
                drawColorChipUsecase.drawColorChip(ShopMapper.toDrawColorChipReqDto(memberId)));
    }
}
