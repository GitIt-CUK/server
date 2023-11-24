package shop.gitit.github.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static shop.gitit.restdochandler.RestDocsHandler.createDocument;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import shop.gitit.github.service.dto.AddPointResDto;
import shop.gitit.github.service.port.in.AddPointUsecase;
import shop.gitit.github.service.port.in.GetCommitsDescriptionUsecase;

@AutoConfigureRestDocs
@WebMvcTest(controllers = GitHubController.class)
class GitHubControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private GetCommitsDescriptionUsecase getCommitsDescriptionUsecase;
    @MockBean private AddPointUsecase addPointUsecase;

    @Test
    @WithMockUser
    void 포인트_적립() throws Exception {
        // given
        String POST_ADD_POINT_URL = "/v1/github/{member-id}";
        long memberId = 1L;
        int commitCount = 10;
        AddPointResDto response = AddPointResDto.builder().message("포인트 적립 완료되었습니다.").build();

        // when
        when(addPointUsecase.addPoint(anyLong(), anyInt())).thenReturn(response);

        // then
        mockMvc.perform(
                        post(POST_ADD_POINT_URL, memberId)
                                .param("commits", String.valueOf(commitCount))
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(createDocument("github/addPoint"));
    }
}
