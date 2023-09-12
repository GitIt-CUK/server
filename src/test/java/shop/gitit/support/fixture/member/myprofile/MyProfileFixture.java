package shop.gitit.support.fixture.member.myprofile;

import shop.gitit.member.domain.myprofile.MyProfile;

public final class MyProfileFixture {

    public static MyProfile getMyProfile() {
        return MyProfile.builder()
                .nickname("닉네임")
                .githubId("깃허브아이디")
                .build();
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
