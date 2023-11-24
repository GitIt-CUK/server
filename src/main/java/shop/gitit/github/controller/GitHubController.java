package shop.gitit.github.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.gitit.github.service.dto.AddPointResDto;
import shop.gitit.github.service.port.in.AddPointUsecase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/github")
public class GitHubController {
    private final AddPointUsecase addPointUsecase;

    @PostMapping("/{member-id}")
    public ResponseEntity<AddPointResDto> addPoint(
            @PathVariable(name = "member-id") long memberId,
            @RequestParam(name = "commits") int commitCount) {
        return ResponseEntity.ok(addPointUsecase.addPoint(memberId, commitCount));
    }
}
