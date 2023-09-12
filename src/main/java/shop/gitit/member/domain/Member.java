package shop.gitit.member.domain;

import static shop.gitit.core.exception.ExceptionEnum.IS_NULL;
import static shop.gitit.member.domain.grasscolor.GrassColor.GREEN;
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
import shop.gitit.member.domain.grasscolor.GrassColor;
import shop.gitit.member.domain.myprofile.MyProfile;
import shop.gitit.member.domain.point.Point;
import shop.gitit.member.domain.status.MemberStatus;
import shop.gitit.member.domain.tier.Tier;
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
    private MyProfile profile;

    @Embedded
    @AttributeOverride(name = "point", column = @Column(name = "member_point", nullable = false))
    private Point point;

    @Column(name = "member_tier")
    private Tier tier;

    @Column(name = "member_grass_color")
    private GrassColor grassColor;

    @ElementCollection
    @CollectionTable(name = "MEMBER_AUTHORITY", joinColumns = @JoinColumn(name = "member_id"))
    private List<Authority> authorities = new ArrayList<>();

    @Column(name = "member_status")
    private MemberStatus status;

    @Builder
    public Member(MyProfile profile, List<Authority> authorities) {
        Assert.notNull(profile, IS_NULL.getMessage());
        Assert.notNull(authorities, IS_NULL.getMessage());
        this.profile = profile;
        this.authorities = authorities;
        this.grassColor = GREEN;
        this.point = new Point();
        this.tier = Tier.init();
        this.status = ACTIVE;
    }

    public void withdrawn() {
        if (alreadyWithdrawn()) {
            throw new AlreadyWithdrawnException();
        }
        this.status = INACTIVE;
    }

    public int getPoint() {
        return this.point.getCoin();
    }

    public void plusPoint(int addedPoint) {
        this.point.plus(addedPoint);
    }

    public void minusPoint(int subPoint) {
        this.point.minus(subPoint);
    }

    private boolean alreadyWithdrawn() {
        return this.status.equals(INACTIVE);
    }
}
