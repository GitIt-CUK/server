package shop.gitit.core.event.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import shop.gitit.github.service.dto.request.ChangeColorChipReqDto;
import shop.gitit.github.service.usecase.ChangeColorChipUsecase;
import shop.gitit.shop.service.event.ChangedColorChipEvent;

@Component
@RequiredArgsConstructor
public class ChangedColorChipHandler {

    private final ChangeColorChipUsecase changeColorChipUsecase;

    @EventListener(ChangedColorChipEvent.class)
    public void changeColorChip(ChangedColorChipEvent event) {
        changeColorChipUsecase.changeColorChip(
                ChangeColorChipReqDto.builder()
                        .memberId(event.getMemberId())
                        .colorCode(event.getColorCode())
                        .build());
    }
}
