package shop.gitit.member.domain.authority;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

    private String role;

    @Builder
    public Authority(String role) {
        this.role = role;
    }
}
