package shop.gitit.shop.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import shop.gitit.shop.service.dto.request.DrawColorChipReqDto;
import shop.gitit.shop.service.dto.response.DrawColorChipResDto;
import shop.gitit.shop.service.usecase.DrawColorChipUsecase;

@AutoConfigureRestDocs
@WebMvcTest(controllers = ShopController.class)
class ShopControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private DrawColorChipUsecase drawColorChipUsecase;

    @Test
    @WithMockUser
    void 컬러칩_뽑기() throws Exception {
        // given
        String DRAW_COLOR_CHIP_URL = "/v1/shop/item/color-chip/{member-id}";
        DrawColorChipResDto response = DrawColorChipResDto.builder().remainingPoint(100).build();

        // when
        when(drawColorChipUsecase.drawColorChip(any(DrawColorChipReqDto.class)))
                .thenReturn(response);

        // then
        mockMvc.perform(
                        post(DRAW_COLOR_CHIP_URL, 1L)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(createDocument("shop/draw_color_chip"));
    }
}
