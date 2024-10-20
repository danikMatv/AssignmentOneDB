package org.example.assignmentonedb.Repository;

import org.example.assignmentonedb.Data.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findByAuthorId(String authorId);
}

