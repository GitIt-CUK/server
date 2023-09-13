package shop.gitit.support.fixture.member.memberprofile;

import shop.gitit.member.domain.memberprofile.MemberProfile;

public final class MemberProfileFixture {

    public static MemberProfile getMyProfile() {
        return MemberProfile.builder().nickname("닉네임").githubId("깃허브아이디").build();
    }

    public static String emptyNickname() {
        return "";
    }

    public static String nullNickname() {
        return null;
    }

    public static String lengthOfNickname(int length) {
        StringBuilder nickname = new StringBuilder();
        return String.valueOf(nickname.append("A".repeat(length)));
    }
}
