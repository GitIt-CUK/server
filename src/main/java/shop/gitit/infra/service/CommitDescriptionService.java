package shop.gitit.infra.service;

import static shop.gitit.core.util.DateUtil.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.PagedIterable;
import org.springframework.stereotype.Service;
import shop.gitit.github.service.dto.response.GetCommitsDescriptionResDto;
import shop.gitit.github.service.port.out.GetCommitDescriptionPort;
import shop.gitit.infra.connector.GitHubConnector;
import shop.gitit.infra.exception.FailConnectToGitHubException;
import shop.gitit.infra.exception.FailConvertToShortInfoException;

@Service
@RequiredArgsConstructor
public class CommitDescriptionService implements GetCommitDescriptionPort {

    private final GitHubConnector connector;

    @Override
    public GetCommitsDescriptionResDto getCommitDescription(String gitHubId) {
        GHUser ghUser = getGhUser(gitHubId);
        List<GHCommit.ShortInfo> result = convertToShortInfo(ghUser);
        return GetCommitsDescriptionResDto.builder()
                .todayCommits(getTodayCommitCount(result))
                .thisWeekCommits(getThisWeekCommitCount(result))
                .serialCommitDay(getSerialCommitCount(result))
                .build();
    }

    private GHUser getGhUser(String gitHubId) {
        try {
            return connector.getGithub().getUser(gitHubId);
        } catch (IOException e) {
            throw new FailConnectToGitHubException(e);
        }
    }

    private List<GHCommit.ShortInfo> convertToShortInfo(GHUser ghUser) {
        List<GHCommit.ShortInfo> shortInfo = new ArrayList<>();
        try {
            List<GHRepository> repositories = new ArrayList<>(ghUser.getRepositories().values());
            for (GHRepository gr : repositories) {
                PagedIterable<GHCommit> commits = gr.listCommits();
                for (GHCommit commit : commits) {
                    shortInfo.add(commit.getCommitShortInfo());
                }
            }
        } catch (IOException e) {
            throw new FailConvertToShortInfoException(e);
        }
        return shortInfo;
    }

    private long getTodayCommitCount(List<GHCommit.ShortInfo> result) {
        LocalDateTime today = getTodayLocalDate();
        return result.stream()
                .filter(it -> isFuture(dateToLocalDateTime(it.getCommitDate()), today))
                .count();
    }

    private long getThisWeekCommitCount(List<GHCommit.ShortInfo> result) {
        LocalDateTime weekAgo = getTodayLocalDate().minusDays(7);
        return result.stream()
                .filter(it -> isFuture(dateToLocalDateTime(it.getCommitDate()), weekAgo))
                .count();
    }

    private int getSerialCommitCount(List<GHCommit.ShortInfo> result) {
        int serialCount = 0;
        LocalDateTime standardDate = getTodayLocalDate();
        List<LocalDateTime> commitDate = sortByCommitDateDesc(result);
        for (LocalDateTime cd : commitDate) {
            if (isPast(standardDate, cd)) {
                break;
            }
            if (isToday(standardDate, cd)) {
                serialCount++;
                standardDate = standardDate.minusDays(1);
            }
        }
        return serialCount;
    }

    private List<LocalDateTime> sortByCommitDateDesc(List<GHCommit.ShortInfo> result) {
        return result.stream()
                .map(it -> dateToLocalDate(it.getCommitDate()))
                .distinct()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }
}
