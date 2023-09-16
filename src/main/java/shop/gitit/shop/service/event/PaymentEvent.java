package shop.gitit.shop.service.event;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentEvent {

    private long payerId;
    private int cost;
}
