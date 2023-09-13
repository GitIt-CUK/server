package shop.gitit.payment.domain.money;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import lombok.Getter;
import shop.gitit.payment.exception.PointViolationException;

@Embeddable
@Getter
public class Money {

    @Transient private static final int ZERO = 0;

    private int point;

    public Money() {
        this.point = ZERO;
    }

    public void plus(int addedPoint) {
        if (addedPoint < ZERO) {
            throw new PointViolationException();
        }
        this.point += addedPoint;
    }

    public void minus(int subPoint) {
        if (this.point < subPoint || subPoint < ZERO) {
            throw new PointViolationException();
        }
        this.point -= subPoint;
    }
}
