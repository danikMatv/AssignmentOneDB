package org.example.assignmentonedb.Controller;

import org.example.assignmentonedb.App.NotFoundException;
import org.example.assignmentonedb.Data.Member;
import org.example.assignmentonedb.Data.SubRequest;
import org.example.assignmentonedb.Node.MemberNode;
import org.example.assignmentonedb.Repository.Neo4jMemberRepository;
import org.example.assignmentonedb.Service.MemberGraphService;
import org.example.assignmentonedb.Service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberGraphService memberGraphService;
    private final Neo4jMemberRepository neo4jMemberRepository;

    public MemberController(MemberService memberService, MemberGraphService memberGraphService, Neo4jMemberRepository neo4jMemberRepository) {
        this.memberService = memberService;
        this.memberGraphService = memberGraphService;
        this.neo4jMemberRepository = neo4jMemberRepository;
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
        memberGraphService.createMemberInGraph(createdMember);
        return ResponseEntity.ok(createdMember);
    }

    @PostMapping("/{memberId}/friends/{friendId}")
    public ResponseEntity<Member> addFriend(@PathVariable String memberId, @PathVariable String friendId) {
        Member updatedMember = memberService.addFriend(memberId, friendId);
        memberGraphService.createFriendshipInGraph(memberId, friendId);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{memberId}/friends/{friendId}")
    public ResponseEntity<Void> removeFriend(@PathVariable String memberId, @PathVariable String friendId) {
        memberService.removeFriend(memberId, friendId);
        memberGraphService.removeFriendshipInGraph(memberId, friendId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find")
    public ResponseEntity<Member> findByEmail(@RequestParam String email) {
        Member member = memberService.findByEmail(email);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{memberId1}/distance/{memberId2}")
    public ResponseEntity<Integer> getFriendshipDistance(@PathVariable String memberId1, @PathVariable String memberId2) {
        int distance = memberService.calculateFriendshipDistance(memberId1, memberId2);
        return ResponseEntity.ok(distance);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Object> removeMember(@PathVariable String memberId) {
        memberService.deleteMember(memberId);
        memberGraphService.deleteMemberInGraph(memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(@RequestBody SubRequest request) {
        memberGraphService.subscribe(request.getSubToId(), request.getSubId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unsubscribe")
    public ResponseEntity<Void> unsubscribe(@RequestBody SubRequest request) {
        memberGraphService.unsubscribe(request.getSubToId(), request.getSubId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{subscriberId}")
    public ResponseEntity<List<MemberNode>> getSubscriptions(@PathVariable String subscriberId) {
        MemberNode subscriber = neo4jMemberRepository.findByMemberId(subscriberId)
                .orElseThrow(() -> new NotFoundException("User not found: " + subscriberId));
        return ResponseEntity.ok(subscriber.getSubscriptions());
    }

}
