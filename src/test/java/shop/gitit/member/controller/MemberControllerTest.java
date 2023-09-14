package shop.gitit.member.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static shop.gitit.restdochandler.RestDocsHandler.createDocument;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import shop.gitit.member.controller.request.UpdateMemberNickNameReq;
import shop.gitit.member.service.dto.request.UpdateMemberNickNameReqDto;
import shop.gitit.member.service.dto.response.UpdateMemberNickNameResDto;
import shop.gitit.member.service.usecase.JoinUsecase;
import shop.gitit.member.service.usecase.UpdateNickNameUsecase;

@AutoConfigureRestDocs
@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private UpdateNickNameUsecase updateNickNameUsecase;
    @MockBean private JoinUsecase joinUsecase;

    @Test
    @WithMockUser
    void 사용자_닉네임_수정() throws Exception {
        // given
        String UPDATE_MEMBER_NICKNAME_URL = "/v1/members/profile/{member-id}";
        UpdateMemberNickNameResDto response =
                UpdateMemberNickNameResDto.builder().memberId(1L).nickName("기릿").build();
        UpdateMemberNickNameReq updateMemberNickNameReq =
                UpdateMemberNickNameReq.builder().nickName("기릿").build();
        String content = objectMapper.writeValueAsString(updateMemberNickNameReq);

        // when
        when(updateNickNameUsecase.updateNickName(any(UpdateMemberNickNameReqDto.class)))
                .thenReturn(response);

        // then
        mockMvc.perform(
                        patch(UPDATE_MEMBER_NICKNAME_URL, 1L)
                                .with(csrf())
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(createDocument("members/profile"));
    }
}
