package shop.gitit.member.controller;

import static shop.gitit.member.controller.mapper.MemberMapper.toReqDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.gitit.member.controller.request.UpdateMemberNickNameReq;
import shop.gitit.member.service.dto.response.UpdateMemberNickNameResDto;
import shop.gitit.member.service.usecase.JoinUsecase;
import shop.gitit.member.service.usecase.UpdateNickNameUsecase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {

    private final JoinUsecase joinUsecase;
    private final UpdateNickNameUsecase updateNickNameUsecase;

    @PatchMapping("/profile/{member-id}")
    public ResponseEntity<UpdateMemberNickNameResDto> updateMemberNickName(
            @PathVariable(name = "member-id") long memberId,
            @RequestBody UpdateMemberNickNameReq req) {
        return ResponseEntity.ok(updateNickNameUsecase.updateNickName(toReqDto(memberId, req)));
    }
}
