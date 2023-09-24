package shop.gitit.github.service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCommitsDescriptionResDto {

    String todayCommits;
    String thisWeekCommits;
    String serialCommitDay;
}
