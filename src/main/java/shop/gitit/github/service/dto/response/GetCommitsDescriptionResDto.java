package shop.gitit.github.service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCommitsDescriptionResDto {

    long todayCommits;
    long thisWeekCommits;
    int serialCommitDay;
}
