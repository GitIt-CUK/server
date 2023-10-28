package shop.gitit.member.repository;

import org.springframework.data.repository.CrudRepository;
import shop.gitit.member.domain.RefreshToken;

public interface RedisRepository extends CrudRepository<RefreshToken, Long> {}
