package shop.gitit.github.controller;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
import org.springframework.test.web.servlet.MockMvc;
import shop.gitit.github.service.dto.response.GetCommitsDescriptionResDto;
import shop.gitit.github.service.port.in.GetCommitsDescriptionUsecase;

@AutoConfigureRestDocs
@WebMvcTest(controllers = GitHubController.class)
class GitHubControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private GetCommitsDescriptionUsecase getCommitsDescriptionUsecase;

    @Test
    void 커밋_요약_정보_조회() throws Exception {
        // given
        String GET_GITHUB_COMMIT_DESC_URL = "/v1/home/commits/description/{member-id}";
        GetCommitsDescriptionResDto response =
                GetCommitsDescriptionResDto.builder()
                        .todayCommits(1)
                        .thisWeekCommits(10)
                        .serialCommitDay(4)
                        .build();

        // when
        when(getCommitsDescriptionUsecase.getCommitsDescription(1L)).thenReturn(response);

        // then
        mockMvc.perform(
                        get(GET_GITHUB_COMMIT_DESC_URL, 1L)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(createDocument("home/commits/description"));
    }
}
