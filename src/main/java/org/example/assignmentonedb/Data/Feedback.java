package org.example.assignmentonedb.Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "feedbacks")
public class Feedback {
    @Id
    private String id;
    private String postId;
    private String authorId;
    private String content;
    private Date createdAt;
    private List<String> reactions;
}

