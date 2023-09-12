package shop.gitit.member.domain.point;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import lombok.Getter;
import shop.gitit.member.exception.PointViolationException;

@Embeddable
@Getter
public class Point {

    @Transient private static final int ZERO = 0;

    private int coin;

    public Point() {
        this.coin = ZERO;
    }

    public void plus(int addedPoint) {
        if (addedPoint < ZERO) {
            throw new PointViolationException();
        }
        this.coin += addedPoint;
    }

    public void minus(int subPoint) {
        if (this.coin < subPoint || subPoint < ZERO) {
            throw new PointViolationException();
        }
        this.coin -= subPoint;
    }
}
