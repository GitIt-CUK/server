package shop.gitit.payment.service.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetPointDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {

        private long memberId;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {

        private long memberId;
        private int point;
    }
}
