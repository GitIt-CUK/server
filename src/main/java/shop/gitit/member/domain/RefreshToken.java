package shop.gitit.member.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "refresh-token", timeToLive = 3 * 24 * 60 * 60) // 3일
public class RefreshToken {

    @Id private Long memberId;
    private String token;
}
