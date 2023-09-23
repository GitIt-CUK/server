package shop.gitit.github.service;

import static shop.gitit.core.util.DateUtil.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHCommit;
import org.springframework.stereotype.Service;
import shop.gitit.github.client.GitHubClient;
import shop.gitit.github.service.dto.response.GetCommitsDescriptionResDto;
import shop.gitit.github.service.usecase.CommitsDescriptionUsecase;
import shop.gitit.member.domain.Member;
import shop.gitit.member.exception.NoMemberException;
import shop.gitit.member.repository.MemberRepository;

// 6
// 93+134+9+22
@Service
@RequiredArgsConstructor
public class CommitsDescriptionService implements CommitsDescriptionUsecase {

    private final GitHubClient githubClient;
    private final MemberRepository memberRepository;

    @Override
    public GetCommitsDescriptionResDto getCommitsDescription(Long memberId) throws IOException {

        Member member =
                memberRepository
                        .findById(memberId)
                        .orElseThrow(() -> new NoMemberException("해당 회원이 존재하지 않습니다."));
        String githubId = member.getProfile().getGithubId();

        // 커밋 내역을 조회한 후, 데이터를 가공
        List<GHCommit.ShortInfo> result = githubClient.getCommits(githubId);

        // 오늘의 커밋 수
        LocalDateTime today = getTodayLocalDate();
        Long todayCommits =
                result.stream()
                        .filter(it -> dateToLocalDateTime(it.getCommitDate()).compareTo(today) > 0)
                        .count();

        /// 이번주 커밋
        LocalDateTime week = today.minusDays(7);
        Long thisWeekCommits =
                result.stream()
                        .filter(it -> dateToLocalDateTime(it.getCommitDate()).compareTo(week) > 0)
                        .count();

        // 연속 일 수 구하기
        Integer serialCount = 0;
        LocalDateTime serialDate = getTodayLocalDate();

        List<LocalDateTime> commitDate =
                result.stream()
                        .map(it -> dateToLocalDate(it.getCommitDate()))
                        .distinct()
                        .sorted(Collections.reverseOrder())
                        .collect(Collectors.toList());

        for (LocalDateTime cd : commitDate) {
            if (cd.compareTo(serialDate) == 0) {
                serialCount++;
                serialDate = serialDate.minusDays(1);
            } else break;
        }

        return GetCommitsDescriptionResDto.builder()
                .todayCommits(todayCommits.toString())
                .thisWeekCommits(thisWeekCommits.toString())
                .serialCommitDay(serialCount.toString())
                .build();
    }
}
