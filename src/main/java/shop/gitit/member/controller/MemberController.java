package shop.gitit.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.gitit.member.controller.request.UpdateMemberNickNameReq;
import shop.gitit.member.service.dto.response.GetMemberProfileResDto;
import shop.gitit.member.service.dto.response.LoginResDto;
import shop.gitit.member.service.dto.response.UpdateMemberNickNameResDto;
import shop.gitit.member.service.usecase.*;

import static shop.gitit.member.controller.mapper.MemberMapper.toUpdateMemberNickNameReqDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {

    private final JoinUsecase joinUsecase;
    private final UpdateNickNameUsecase updateNickNameUsecase;
    private final GetProfileUsecase getProfileUsecase;
    private final WithdrawnUsecase withdrawnUsecase;
    private final OauthUsecase oauthUsecase;

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

    @PatchMapping("/withdrawn/{member-id}")
    public ResponseEntity withdrawMember(@PathVariable(name = "member-id") long memberId) {
        withdrawnUsecase.withdrawMember(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/login/oauth/{provider}/{code}")
    public ResponseEntity<LoginResDto> login(@PathVariable String provider, @PathVariable String code) {
        return ResponseEntity.ok(oauthUsecase.login(provider, code));
    }
}
