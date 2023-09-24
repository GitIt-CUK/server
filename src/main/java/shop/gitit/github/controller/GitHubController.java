package shop.gitit.github.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.gitit.github.service.dto.response.GetCommitsDescriptionResDto;
import shop.gitit.github.service.usecase.CommitsDescriptionUsecase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/home")
public class GitHubController {

    private final CommitsDescriptionUsecase commitsDescriptionUsecase;

    @GetMapping("/commits/description/{member-id}")
    public ResponseEntity<GetCommitsDescriptionResDto> getCommitsDescription(
            @PathVariable(name = "member-id") long memberId) throws IOException {
        return ResponseEntity.ok(commitsDescriptionUsecase.getCommitsDescription(memberId));
    }
}
