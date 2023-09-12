package shop.gitit.support.fixture.member.authority;

import shop.gitit.member.domain.authority.Authority;

import java.util.List;

public class AuthorityFixture {

    public static List<Authority> getMemberAuth() {
        return List.of(Authority.builder().role("MEMBER").build());
    }
}
