package org.example.assignmentonedb.Service;

import org.example.assignmentonedb.Data.Member;
import org.example.assignmentonedb.Node.MemberNode;
import org.example.assignmentonedb.Repository.Neo4jMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberGraphService {

    @Autowired
    private Neo4jMemberRepository neo4jMemberRepository;

    public void createMemberInGraph(Member member) {
        MemberNode userNode = new MemberNode(member.getId(), member.getFirstName(), member.getEmail());
        neo4jMemberRepository.save(userNode);
    }

    public void deleteMemberInGraph(String memberId) {
        neo4jMemberRepository.deleteById(memberId);
    }

    public void createFriendshipInGraph(String memberId, String friendId) {
        MemberNode member = neo4jMemberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        MemberNode friend = neo4jMemberRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found"));

        member.getFriends().add(friend);
        neo4jMemberRepository.save(member);
    }

    public void removeFriendshipInGraph(String memberId, String friendId) {
        MemberNode member = neo4jMemberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        MemberNode friend = neo4jMemberRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found"));

        member.getFriends().remove(friend);
        neo4jMemberRepository.save(member);
    }

    public void subscribe(String subscriberId, String subscribedUserId) {
        MemberNode subscriber = neo4jMemberRepository.findById(subscriberId)
                .orElseThrow(() -> new RuntimeException("Subscriber not found"));
        MemberNode subscribedUser = neo4jMemberRepository.findById(subscribedUserId)
                .orElseThrow(() -> new RuntimeException("Subscribed user not found"));

        subscriber.getSubscriptions().add(subscribedUser);
        neo4jMemberRepository.save(subscriber);
    }

    public void unsubscribe(String subscriberId, String subscribedUserId) {
        MemberNode subscriber = neo4jMemberRepository.findById(subscriberId)
                .orElseThrow(() -> new RuntimeException("Subscriber not found"));
        MemberNode subscribedUser = neo4jMemberRepository.findById(subscribedUserId)
                .orElseThrow(() -> new RuntimeException("Subscribed user not found"));

        subscriber.getSubscriptions().remove(subscribedUser);
        neo4jMemberRepository.save(subscriber);
    }

    public int getFriendshipDistance(String memberId1, String memberId2) {
        return neo4jMemberRepository.calculateFriendshipDistance(memberId1, memberId2);
    }
}

