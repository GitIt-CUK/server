package shop.gitit.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.gitit.member.service.usecase.JoinUsecase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {

    private final JoinUsecase joinUsecase;
}
