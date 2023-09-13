package shop.gitit.payment.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static shop.gitit.support.restdochandler.RestDocsHandler.createDocument;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import shop.gitit.payment.service.dto.GetPointDto;
import shop.gitit.payment.service.usecase.GetPointUsecase;

@AutoConfigureRestDocs
@WebMvcTest(controllers = PaymentController.class)
class PaymentControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private GetPointUsecase getPointUsecase;

    @Test
    @WithMockUser
    void 포인트_조회() throws Exception {
        // given
        String GET_POINT_URL = "/v1/pay/point/{member-id}";
        GetPointDto.Response response =
                GetPointDto.Response.builder().memberId(1L).point(100).build();

        // when
        when(getPointUsecase.getPoint(any(GetPointDto.Request.class))).thenReturn(response);

        // then
        mockMvc.perform(get(GET_POINT_URL, 1L).with(csrf()).contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(createDocument("pay/point"));
    }
}
