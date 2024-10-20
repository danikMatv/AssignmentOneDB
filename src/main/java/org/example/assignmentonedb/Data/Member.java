package org.example.assignmentonedb.Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "members")
public class Member {
    @Id
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> interests;
    private List<String> friends;
}

