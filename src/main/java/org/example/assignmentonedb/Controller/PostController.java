package org.example.assignmentonedb.Controller;

import org.example.assignmentonedb.Data.Post;
import org.example.assignmentonedb.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<Post>> getPostsByMemberId(@PathVariable String memberId) {
        List<Post> posts = postService.getPostsByMemberId(memberId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/id/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable String postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Post>> getAllPostsSortedByDate() {
        List<Post> posts = postService.getAllPostsSortedByDate();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/{postId}/reactions")
    public ResponseEntity<Void> addReactionToPost(@PathVariable String postId, @RequestBody List<String> reactions) {
        postService.addReactionToPost(postId, reactions);
        return ResponseEntity.noContent().build();
    }
}
