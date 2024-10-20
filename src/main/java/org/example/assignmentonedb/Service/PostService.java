package org.example.assignmentonedb.Service;

import org.example.assignmentonedb.Data.Post;
import org.example.assignmentonedb.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        post.setCreatedAt(new Date());
        return postRepository.save(post);
    }

    public List<Post> getPostsByMemberId(String memberId) {
        return postRepository.findByAuthorId(memberId);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(String postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public List<Post> getAllPostsSortedByDate() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public void addReactionToPost(String postId, String reaction) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        if (post.getReactions() == null) {
            post.setReactions(new ArrayList<>());
        }
        post.getReactions().add(reaction);
        postRepository.save(post);
    }
}
