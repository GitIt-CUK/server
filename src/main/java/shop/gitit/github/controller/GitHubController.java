package shop.gitit.github.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.gitit.github.service.dto.response.GetCommitsDescriptionResDto;
import shop.gitit.github.service.port.in.GetCommitsDescriptionUsecase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/home")
public class GitHubController {

    private final GetCommitsDescriptionUsecase getCommitsDescriptionUsecase;

    @GetMapping("/commits/description/{member-id}")
    public ResponseEntity<GetCommitsDescriptionResDto> getCommitsDescription(
            @PathVariable(name = "member-id") long memberId) throws IOException {
        return ResponseEntity.ok(getCommitsDescriptionUsecase.getCommitsDescription(memberId));
    }
}
