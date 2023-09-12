package shop.gitit.support.fixture.member.point;

import shop.gitit.member.domain.point.Point;

public class PointFixture {

    public static Point getPoint() {
        return new Point();
    }

    public static int commitCountOf(int commitCount) {
        return commitCount;
    }

    public static int subPointOf(int point) {
        return point;
    }
}
