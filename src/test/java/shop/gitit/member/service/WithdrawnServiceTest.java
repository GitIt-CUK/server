package shop.gitit.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static shop.gitit.member.domain.status.MemberStatus.INACTIVE;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.gitit.member.domain.Member;
import shop.gitit.member.domain.support.member.MemberFixture;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.service.usecase.WithdrawnUsecase;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WithdrawnService.class})
class WithdrawnServiceTest {

    @Autowired private WithdrawnUsecase withdrawnUsecase;
    @MockBean private MemberRepository memberRepository;

    @DisplayName("withdrawMember 메서드는 멤버의 withdrawn 필드의 상태값을 INACTIVE로 변경한다.")
    @Test
    void withdrawMember() {
        // given
        Member member = MemberFixture.getMember();

        // when
        when(memberRepository.findById(anyLong())).thenReturn(Optional.ofNullable(member));
        withdrawnUsecase.withdrawMember(1L);

        // then
        assertThat(member.getStatus()).isEqualTo(INACTIVE);
    }
}
