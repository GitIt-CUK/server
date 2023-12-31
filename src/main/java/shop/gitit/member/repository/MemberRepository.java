package shop.gitit.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.gitit.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from MEMBER m where m.profile.githubId = :githubId")
    Optional<Member> findByGithubId(@Param("githubId") String githubId);
}
