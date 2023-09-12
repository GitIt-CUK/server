package shop.gitit.support.fixture.member;

import shop.gitit.member.domain.Member;
import shop.gitit.support.fixture.member.authority.AuthorityFixture;
import shop.gitit.support.fixture.member.myprofile.MyProfileFixture;

public class MemberFixture {

    private static final int NEGATIVE_POINT = -1;
    private static final int POSITIVE_POINT = 10;

    public static Member getMember() {
        return Member.builder()
                .profile(MyProfileFixture.getMyProfile())
                .authorities(AuthorityFixture.getMemberAuth())
                .build();
    }

    public static int negativePoint() {
        return NEGATIVE_POINT;
    }

    public static int positivePoint() {
        return POSITIVE_POINT;
    }

    public static int pointOf(int point) {
        return point;
    }
}
