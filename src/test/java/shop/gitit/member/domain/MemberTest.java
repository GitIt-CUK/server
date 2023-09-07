package shop.gitit.member.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static shop.gitit.member.domain.rankinfo.Tier.GOLD3;
import static shop.gitit.member.domain.status.MemberStatus.INACTIVE;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.member.domain.authority.Authority;
import shop.gitit.member.domain.myprofile.MyProfile;
import shop.gitit.member.domain.rankinfo.RankInfo;
import shop.gitit.member.exception.AlreadyWithdrawnException;

class MemberTest {

    private final Member member =
            Member.builder()
                    .profile(MyProfile.builder().githubId("githubId").nickname("닉네임").build())
                    .rankInfo(RankInfo.builder().build())
                    .authorities(List.of(Authority.builder().role("MEMBER").build()))
                    .build();

    @DisplayName("닉네임 변경에 성공한다")
    @Test
    void updateNickname() {
        // given
        String nickname = "변경닉네임";

        // when
        member.updateNickname(nickname);

        // then
        assertThat(member.getNickname()).isEqualTo(nickname);
    }

    @DisplayName("누적 커밋 수를 갱신하면 랭크 정보가 갱신된다")
    @Test
    void updateRankInfo() {
        // given
        int commitCount = 100;

        // when
        member.updateRankInfo(commitCount);

        // then
        assertThat(member)
                .extracting(Member::getCommitCount, Member::getTier)
                .contains(commitCount, GOLD3);
    }

    @DisplayName("탈퇴에 성공한다")
    @Test
    void withdrawn() {
        // given

        // when
        member.withdrawn();

        // then
        assertThat(member.getStatus()).isEqualTo(INACTIVE);
    }

    @DisplayName("이미 탈퇴한 회원이 탈퇴를 시도하면 에러를 던진다")
    @Test
    void alreadyWithdrawn() {
        // given

        // when
        member.withdrawn();

        // then
        assertThatThrownBy(() -> member.withdrawn()).isInstanceOf(AlreadyWithdrawnException.class);
    }
}
