package shop.gitit.support.fixture.member;

import shop.gitit.member.domain.Member;
import shop.gitit.support.fixture.member.authority.AuthorityFixture;
import shop.gitit.support.fixture.member.memberprofile.MemberProfileFixture;

public class MemberFixture {

    public static Member getMember() {
        return Member.builder()
                .profile(MemberProfileFixture.getMyProfile())
                .authorities(AuthorityFixture.getMemberAuth())
                .build();
    }
}
