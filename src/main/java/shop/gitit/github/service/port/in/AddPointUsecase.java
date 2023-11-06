package shop.gitit.github.service.port.in;

import shop.gitit.github.service.dto.AddPointResDto;

public interface AddPointUsecase {

    AddPointResDto addPoint(long memberId, int commitCount);
}
