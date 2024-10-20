package org.example.assignmentonedb.Controller;

import org.example.assignmentonedb.Data.Member;
import org.example.assignmentonedb.Service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestParam String email, @RequestParam String password) {
        Member member = memberService.login(email, password);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(member);
    }


    @PostMapping("/register")
    public ResponseEntity<Member> register(@RequestBody Member member) {
        Member createdMember = memberService.register(member);
        return ResponseEntity.ok(createdMember);
    }

    @PostMapping("/{memberId}/friends/{friendId}")
    public ResponseEntity<Member> addFriend(@PathVariable String memberId, @PathVariable String friendId) {
        Member updatedMember = memberService.addFriend(memberId, friendId);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{memberId}/friends/{friendId}")
    public ResponseEntity<Void> removeFriend(@PathVariable String memberId, @PathVariable String friendId) {
        memberService.removeFriend(memberId, friendId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find")
    public ResponseEntity<Member> findByEmail(@RequestParam String email) {
        Member member = memberService.findByEmail(email);
        return ResponseEntity.ok(member);
    }
}
