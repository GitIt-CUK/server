package shop.gitit.member.domain;

import static shop.gitit.core.exception.ExceptionEnum.IS_NULL;
import static shop.gitit.member.domain.status.MemberStatus.ACTIVE;
import static shop.gitit.member.domain.status.MemberStatus.INACTIVE;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.Assert;
import shop.gitit.core.basefield.BaseField;
import shop.gitit.member.domain.authority.Authority;
import shop.gitit.member.domain.myprofile.MyProfile;
import shop.gitit.member.domain.rankinfo.RankInfo;
import shop.gitit.member.domain.rankinfo.Tier;
import shop.gitit.member.domain.status.MemberStatus;
import shop.gitit.member.exception.AlreadyWithdrawnException;

@Document(collection = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseField {

    @Id
    @Field(name = "member_id")
    private ObjectId id;

    @Field(name = "member_profile")
    private MyProfile profile;

    @Field(name = "member_rank_info")
    private RankInfo rankInfo;

    @Field(name = "member_status")
    private MemberStatus status;

    @Field(name = "member_authorities")
    private List<Authority> authorities = new ArrayList<>();

    @Builder
    public Member(MyProfile profile, RankInfo rankInfo, List<Authority> authorities) {
        Assert.notNull(profile, IS_NULL.getMessage());
        Assert.notNull(rankInfo, IS_NULL.getMessage());
        Assert.notNull(authorities, IS_NULL.getMessage());
        this.profile = profile;
        this.rankInfo = rankInfo;
        this.authorities = authorities;
        this.status = ACTIVE;
    }

    public ObjectId getId() {
        return id;
    }

    public String getNickname() {
        return profile.getNickname();
    }

    public void updateNickname(String nickname) {
        this.profile.updateNickname(nickname);
    }

    public int getCommitCount() {
        return this.rankInfo.getCommitCount();
    }

    public Tier getTier() {
        return this.rankInfo.getTier();
    }

    public void updateRankInfo(int commitCount) {
        this.rankInfo.updateRankInfo(commitCount);
    }

    public MemberStatus getStatus() {
        return status;
    }

    public List<Authority> getAuthorities() {
        return authorities;
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
