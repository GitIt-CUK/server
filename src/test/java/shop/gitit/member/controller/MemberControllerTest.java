package shop.gitit.member.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
import shop.gitit.member.domain.memberprofile.MemberProfile;
import shop.gitit.member.domain.support.member.memberprofile.MemberProfileFixture;
import shop.gitit.member.service.dto.ReissueTokenResDto;
import shop.gitit.member.service.dto.request.UpdateMemberNickNameReqDto;
import shop.gitit.member.service.dto.response.GetMemberProfileResDto;
import shop.gitit.member.service.dto.response.LoginResDto;
import shop.gitit.member.service.dto.response.UpdateMemberNickNameResDto;
import shop.gitit.member.service.port.in.*;

@AutoConfigureRestDocs
@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private UpdateNickNameUsecase updateNickNameUsecase;
    @MockBean private GetProfileUsecase getProfileUsecase;
    @MockBean private WithdrawnUsecase withdrawnUsecase;
    @MockBean private LoginUsecase loginUsecase;
    @MockBean private ReissueTokenUsecase reissueTokenUsecase;

    @Test
    @WithMockUser
    void 사용자_닉네임_수정() throws Exception {
        // given
        String UPDATE_MEMBER_NICKNAME_URL = "/v1/members/profile/{member-id}";
        UpdateMemberNickNameResDto response =
                UpdateMemberNickNameResDto.builder().nickname("기릿").build();
        UpdateMemberNickNameReq updateMemberNickNameReq =
                UpdateMemberNickNameReq.builder().nickname("기릿").build();
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

    @Test
    @WithMockUser
    void 사용자_프로필_조회() throws Exception {
        // given
        String GET_MEMBER_PROFILE_URL = "/v1/members/profile/{member-id}";
        MemberProfile memberProfile = MemberProfileFixture.getMyProfile();
        GetMemberProfileResDto response =
                GetMemberProfileResDto.builder()
                        .githubId(memberProfile.getGithubId())
                        .nickname(memberProfile.getNickname())
                        .build();

        // when
        when(getProfileUsecase.getMemberProfile(anyLong())).thenReturn(response);

        // then
        mockMvc.perform(
                        get(GET_MEMBER_PROFILE_URL, 1L)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(createDocument("get/members/profile"));
    }

    @Test
    @WithMockUser
    void 사용자_탈퇴() throws Exception {
        // given
        String PATCH_MEMBER_WITHDRAWN_URL = "/v1/members/withdrawn/{member-id}";
        MemberProfile memberProfile = MemberProfileFixture.getMyProfile();

        // when

        // then
        mockMvc.perform(
                        patch(PATCH_MEMBER_WITHDRAWN_URL, 1L)
                                .with(csrf())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk())
                .andDo(print())
                .andDo(createDocument("withdrawn/members"));
    }

    @Test
    @WithMockUser
    void 깃허브_로그인() throws Exception {
        // given
        String POST_LOGIN_OAUTH_URL = "/v1/members/login/oauth";
        String code = "github-access-token";
        LoginResDto dto =
                LoginResDto.builder()
                        .memberId(1L)
                        .accessToken("access-token")
                        .refreshToken("refresh-token")
                        .build();

        // when
        when(loginUsecase.login(anyString())).thenReturn(dto);

        // then
        mockMvc.perform(
                        post(POST_LOGIN_OAUTH_URL)
                                .param("code", code)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(createDocument("members/github/login"));
    }

    @Test
    @WithMockUser
    void ATK_재발급() throws Exception {
        // given
        String POST_REISSUE_TOKEN_URL = "/v1/members/{member-id}/token/{refresh-token}";
        ReissueTokenResDto dto =
                ReissueTokenResDto.builder().accessToken("new-access-token").build();

        // when
        when(reissueTokenUsecase.reissueToken(anyLong(), anyString())).thenReturn(dto);

        // then
        mockMvc.perform(
                        post(POST_REISSUE_TOKEN_URL, 1L, "RTK")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(createDocument("members/reissue/token"));
    }
}
