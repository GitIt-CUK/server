package shop.gitit.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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
import shop.gitit.member.service.dto.request.UpdateMemberNickNameReqDto;
import shop.gitit.member.service.dto.response.UpdateMemberNickNameResDto;
import shop.gitit.member.service.usecase.UpdateNickNameUsecase;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UpdateNickNameService.class})
class UpdateNickNameServiceTest {

    @Autowired private UpdateNickNameUsecase updateNickNameUsecase;
    @MockBean private MemberRepository memberRepository;

    @DisplayName("updateNickName 메서드는 닉네임을 변경한다.")
    @Test
    void updateNickName() {
        // given
        UpdateMemberNickNameReqDto request =
                UpdateMemberNickNameReqDto.builder().memberId(1L).nickname("기릿").build();
        Member member = MemberFixture.getMember();

        // when
        when(memberRepository.findById(anyLong())).thenReturn(Optional.ofNullable(member));
        UpdateMemberNickNameResDto result = updateNickNameUsecase.updateNickName(request);

        // then
        assertThat(result).extracting(UpdateMemberNickNameResDto::getNickname).as("기릿");
    }
}
