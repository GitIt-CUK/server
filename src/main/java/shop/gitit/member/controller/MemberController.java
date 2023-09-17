package shop.gitit.member.controller;

import static shop.gitit.member.controller.mapper.MemberMapper.toUpdateMemberNickNameReqDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.gitit.member.controller.request.UpdateMemberNickNameReq;
import shop.gitit.member.service.dto.response.GetMemberProfileResDto;
import shop.gitit.member.service.dto.response.UpdateMemberNickNameResDto;
import shop.gitit.member.service.usecase.GetProfileUsecase;
import shop.gitit.member.service.usecase.JoinUsecase;
import shop.gitit.member.service.usecase.UpdateNickNameUsecase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {

    private final JoinUsecase joinUsecase;
    private final UpdateNickNameUsecase updateNickNameUsecase;
    private final GetProfileUsecase getProfileUsecase;

    @PatchMapping("/profile/{member-id}")
    public ResponseEntity<UpdateMemberNickNameResDto> updateMemberNickName(
            @PathVariable(name = "member-id") long memberId,
            @RequestBody UpdateMemberNickNameReq req) {
        return ResponseEntity.ok(
                updateNickNameUsecase.updateNickName(toUpdateMemberNickNameReqDto(memberId, req)));
    }

    @GetMapping("/profile/{member-id}")
    public ResponseEntity<GetMemberProfileResDto> getMemberProfile(
            @PathVariable(name = "member-id") long memberId) {
        return ResponseEntity.ok(getProfileUsecase.getMemberProfile(memberId));
    }
}
