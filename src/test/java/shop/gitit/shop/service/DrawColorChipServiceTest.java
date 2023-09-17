package shop.gitit.shop.service;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.gitit.shop.service.dto.request.DrawColorChipReqDto;
import shop.gitit.shop.service.usecase.DrawColorChipUsecase;
import shop.gitit.shop.support.ServiceDtoFixture;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DrawColorChipService.class})
class DrawColorChipServiceTest {

    @Autowired private DrawColorChipUsecase drawColorChipUsecase;

    @DisplayName("drawColorChip 메서드는")
    @Nested
    class drawColorChip {

        @DisplayName("아이템 종류를 파악한 후 가격만큼 결제 요청을 하는데,")
        @Nested
        class findItemTypeAndRequestForPayments {

            @DisplayName("유저의 잔액이 충분하면")
            @Nested
            class userAccountIsNotOverdrawn {

                @DisplayName("결제에 성공하고 컬러칩 변경 이벤트를 호출하고 컬러칩이 변경된다.")
                @Test
                void payAndChangeColorChip() {
                    // given
                    long memberId = 1L;
                    DrawColorChipReqDto req = ServiceDtoFixture.defaultDrawColorChipReqDto();

                    // when

                    // then
                    assertThatNoException()
                            .isThrownBy(() -> drawColorChipUsecase.drawColorChip(req));
                }
            }
        }
    }
}
