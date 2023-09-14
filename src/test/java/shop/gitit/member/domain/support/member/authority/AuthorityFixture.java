package shop.gitit.member.domain.support.member.authority;

import java.util.List;
import shop.gitit.member.domain.authority.Authority;

public class AuthorityFixture {

    public static List<Authority> getMemberAuth() {
        return List.of(Authority.builder().role("MEMBER").build());
    }
}