package shop.gitit.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gitit.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {}
