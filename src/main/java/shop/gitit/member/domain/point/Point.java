package shop.gitit.member.domain.point;

import lombok.Getter;
import shop.gitit.member.exception.PointViolationException;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Getter
public class Point {

    @Transient
    private static final int ZERO = 0;

    private int point;

    public Point() {
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
