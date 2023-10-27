package shop.gitit.member.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReissueTokenResDto {

    private String accessToken;
}
