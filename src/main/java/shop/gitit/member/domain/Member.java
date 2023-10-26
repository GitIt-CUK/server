package shop.gitit.member.domain;

import static shop.gitit.core.exception.ExceptionEnum.IS_NULL;
import static shop.gitit.member.domain.status.MemberStatus.ACTIVE;
import static shop.gitit.member.domain.status.MemberStatus.INACTIVE;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import shop.gitit.core.baseentity.BaseEntity;
import shop.gitit.member.domain.authority.Authority;
import shop.gitit.member.domain.memberprofile.MemberProfile;
import shop.gitit.member.domain.status.MemberStatus;
import shop.gitit.member.exception.AlreadyWithdrawnException;

@Entity(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Embedded
    @AttributeOverride(
            name = "githubId",
            column = @Column(name = "member_github_id", nullable = false))
    @AttributeOverride(
            name = "nickname",
            column = @Column(name = "member_nickname", nullable = false))
    @AttributeOverride(
            name = "profileImg",
            column = @Column(name = "member_profile_img", nullable = false))
    private MemberProfile profile;

    @ElementCollection
    @CollectionTable(name = "MEMBER_AUTHORITY", joinColumns = @JoinColumn(name = "member_id"))
    private List<Authority> authorities = new ArrayList<>();

    @Column(name = "member_status")
    private MemberStatus status;

    @Builder
    public Member(MemberProfile profile, List<Authority> authorities) {
        Assert.notNull(profile, IS_NULL.getMessage());
        Assert.notNull(authorities, IS_NULL.getMessage());
        this.profile = profile;
        this.authorities = authorities;
        this.status = ACTIVE;
    }

    public void withdrawn() {
        if (alreadyWithdrawn()) {
            throw new AlreadyWithdrawnException();
        }
        this.status = INACTIVE;
    }

    private boolean alreadyWithdrawn() {
        return this.status.equals(INACTIVE);
    }
}
