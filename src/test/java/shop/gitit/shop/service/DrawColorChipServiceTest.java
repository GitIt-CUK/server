package shop.gitit.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.gitit.github.repository.GitHubInfoRepository;
import shop.gitit.payment.repository.PaymentRepository;
import shop.gitit.shop.service.usecase.DrawColorChipUsecase;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DrawColorChipService.class})
class DrawColorChipServiceTest {

    @Autowired private DrawColorChipUsecase drawColorChipUsecase;
    @MockBean private PaymentRepository paymentRepository;
    @MockBean private GitHubInfoRepository gitHubInfoRepository;

    @DisplayName("drawColorChip 메서드는")
    @Nested
    class drawColorChip {}
}
