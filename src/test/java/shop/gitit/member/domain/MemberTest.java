package shop.gitit.member.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static shop.gitit.member.domain.status.MemberStatus.INACTIVE;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.member.domain.support.member.MemberFixture;
import shop.gitit.member.exception.AlreadyWithdrawnException;

class MemberTest {

    private Member member = MemberFixture.getMember();

    @DisplayName("이미 탈퇴한 회원이 탈퇴 요청 시 에러를 던진다.")
    @Test
    void alreadyWithdrawn() {
        // given

        // when
        member.withdrawn();

        // then
        assertThatThrownBy(() -> member.withdrawn()).isInstanceOf(AlreadyWithdrawnException.class);
    }

    @DisplayName("회원탈퇴를 하면 상태값이 변경된다.")
    @Test
    void withdrawn() {
        // given

        // when
        member.withdrawn();

        // then
        assertThat(member.getStatus()).isEqualTo(INACTIVE);
    }
}
