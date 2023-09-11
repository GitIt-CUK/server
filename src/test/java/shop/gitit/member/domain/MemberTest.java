package shop.gitit.member.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static shop.gitit.member.domain.color.GrassColor.DIAMOND;
import static shop.gitit.member.domain.rankinfo.Tier.GOLD3;
import static shop.gitit.member.domain.status.MemberStatus.INACTIVE;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.member.domain.authority.Authority;
import shop.gitit.member.domain.color.GrassColor;
import shop.gitit.member.domain.goods.Goods;
import shop.gitit.member.domain.myprofile.MyProfile;
import shop.gitit.member.domain.rankinfo.RankInfo;
import shop.gitit.member.exception.AlreadyWithdrawnException;
import shop.gitit.member.exception.PointViolationException;

class MemberTest {

    private final Member member =
            Member.builder()
                    .profile(MyProfile.builder().githubId("githubId").nickname("닉네임").build())
                    .rankInfo(RankInfo.builder().build())
                    .authorities(List.of(Authority.builder().role("MEMBER").build()))
                    .color(GrassColor.RED)
                    .goods(Goods.builder().build())
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
                .extracting(Member::getCommitCount, Member::getTier, Member::getPoint)
                .contains(commitCount, GOLD3, 1000);
    }

    @DisplayName("색 뽑기를 하면 포인트가 감소하고 색이 갱신된다")
    @Test
    void colorDrawSuccess() {
        // given
        int subPoint = 0;
        GrassColor color = DIAMOND;

        // when
        member.colorDraw(subPoint, color);

        // then
        assertThat(member).extracting(Member::getColor, Member::getPoint).contains(DIAMOND, 0);
    }

    @DisplayName("보유한 포인트보다 많은 포인트가 감소하면 에러가 발생한다")
    @Test
    void colorDrawBiggerPoint() {
        // given
        int subPoint = 1;
        GrassColor color = DIAMOND;

        // then
        assertThatThrownBy(() -> member.colorDraw(subPoint, color))
                .isInstanceOf(PointViolationException.class);
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
