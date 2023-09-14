package shop.gitit.member.domain.support.member;

import shop.gitit.member.domain.Member;
import shop.gitit.member.domain.support.member.authority.AuthorityFixture;
import shop.gitit.member.domain.support.member.memberprofile.MemberProfileFixture;

public class MemberFixture {

    public static Member getMember() {
        return Member.builder()
                .profile(MemberProfileFixture.getMyProfile())
                .authorities(AuthorityFixture.getMemberAuth())
                .build();
    }
}
