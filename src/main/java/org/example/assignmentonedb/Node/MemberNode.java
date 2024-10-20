package org.example.assignmentonedb.Node;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Getter
@Setter
@Node("Member")
public class MemberNode {

    @Id
    private String id;
    private String firstName;
    private String email;

    @Relationship(type = "FRIEND", direction = Relationship.Direction.OUTGOING)
    private List<MemberNode> friends;

    @Relationship(type = "SUBSCRIBED", direction = Relationship.Direction.OUTGOING)
    private List<MemberNode> subscriptions;


    public MemberNode(String id, String email, String firstName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
    }
}
