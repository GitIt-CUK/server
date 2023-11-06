package shop.gitit.github.domain.exchanger;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ExchangerTest {

    @DisplayName("Exchanger 객체는")
    @Nested
    class ExchangerClass {

        @DisplayName("커밋 수를 입력받으면")
        @Nested
        class whenInputCommitCount {
            final int commitCount = 10;

            @DisplayName("커밋 당 20포인트만큼 계산하여 반환한다.")
            @Test
            void exchangeToPoint() {
                assertThat(Exchanger.exchangeToPoint(commitCount)).isEqualTo(10 * 20);
            }
        }
    }
}
