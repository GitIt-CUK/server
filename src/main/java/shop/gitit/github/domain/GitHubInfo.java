package shop.gitit.github.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gitit.core.baseentity.BaseEntity;
import shop.gitit.github.domain.commitcount.CommitCount;

import javax.persistence.*;

@Entity
@Table(name = "GITHUB_INFO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GitHubInfo extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "github_info_id")
    private Long id;

    @Embedded
    @AttributeOverride(name = "count", column = @Column(name = "github_info_commit_count", nullable = false))
    private CommitCount commitCount;
}
