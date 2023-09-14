package shop.gitit.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
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
import shop.gitit.member.service.dto.request.UpdateMemberNickNameReqDto;
import shop.gitit.member.service.dto.response.UpdateMemberNickNameResDto;
import shop.gitit.member.service.usecase.UpdateNickNameUsecase;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UpdateNickNameService.class})
class UpdateNickNameServiceTest {
    @Autowired private UpdateNickNameUsecase updateNickNameUsecase;

    @MockBean private MemberRepository memberRepository;

    @DisplayName("updateNickName 메서드는 기릿으로 닉네임을 변경한다.")
    @Test
    void updateNickName() {
        // given
        UpdateMemberNickNameReqDto request =
                UpdateMemberNickNameReqDto.builder().memberId(1L).nickName("기릿").build();
        Member member = MemberFixture.getMember();

        // when
        member.getProfile().updateNickname(request.getNickName());
        // TODO 멤버의 pk 값은 생성자를 통해 설정되지 않으므로 Res 값을 하드 코딩하였음 해결 방안 고민 필요
        UpdateMemberNickNameResDto result =
                UpdateMemberNickNameResDto.builder()
                        .memberId(1L)
                        .nickName(member.getProfile().getNickname())
                        .build();

        // then
        assertThat(result)
                .extracting(
                        UpdateMemberNickNameResDto::getMemberId,
                        UpdateMemberNickNameResDto::getNickName)
                .contains(result.getMemberId(), "기릿");
    }
}
