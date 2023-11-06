package shop.gitit.github.domain.exchanger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exchanger {

    private static final int POINT_PER_COMMIT = 20;

    public static int exchangeToPoint(int commitCount) {
        return commitCount * POINT_PER_COMMIT;
    }
}
