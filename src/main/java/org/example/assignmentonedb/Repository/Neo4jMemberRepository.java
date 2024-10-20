package org.example.assignmentonedb.Repository;

import org.example.assignmentonedb.Node.MemberNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Neo4jMemberRepository extends Neo4jRepository<MemberNode, String> {

    @Query("MATCH (m:Member {id: $memberId}) RETURN m")
    Optional<MemberNode> findByMemberId(@Param("memberId") String memberId);

    @Query("MATCH (m1:Member {id: $memberId1}), (m2:Member {id: $memberId2}), " +
            "p = shortestPath((m1)-[:FRIEND*]-(m2)) " +
            "RETURN length(p)")
    int calculateFriendshipDistance(@Param("memberId1") String memberId1, @Param("memberId2") String memberId2);
}

