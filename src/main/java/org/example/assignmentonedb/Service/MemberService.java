package org.example.assignmentonedb.Service;

import org.example.assignmentonedb.Data.Member;
import org.example.assignmentonedb.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member login(String email, String password) {
        return memberRepository.findByEmailAndPassword(email, password);
    }

    public Member addFriend(String memberId, String friendId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Member friend = memberRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found"));
        member.getFriends().add(friendId);
        friend.getFriends().add(memberId);

        memberRepository.save(member);
        memberRepository.save(friend);
        return member;
    }

    public Member register(Member member) {
        return memberRepository.save(member);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public void removeFriend(String memberId, String friendId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Member friend = memberRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found"));
        member.getFriends().remove(friendId);
        friend.getFriends().remove(memberId);

        memberRepository.save(member);
        memberRepository.save(friend);
    }
}
