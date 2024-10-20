//package org.example.assignmentonedb.App;
//
//import org.example.assignmentonedb.Data.Member;
//import org.example.assignmentonedb.Data.Post;
//import org.example.assignmentonedb.Service.MemberService;
//import org.example.assignmentonedb.Service.PostService;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Scanner;
//
//@Component
//public class SocialNetworkConsoleApp {
//
//    private final MemberService memberService;
//    private final PostService postService;
//
//    public SocialNetworkConsoleApp(MemberService memberService, PostService postService) {
//        this.memberService = memberService;
//        this.postService = postService;
//    }
//
//    public void loginAndMenu() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter email: ");
//        String email = scanner.nextLine();
//        System.out.println("Enter password: ");
//        String password = scanner.nextLine();
//
//        Member member = memberService.login(email, password);
//
//        if (member != null) {
//            System.out.println("Welcome, " + member.getFirstName() + "!");
//            showMenu(member);
//        } else {
//            System.out.println("Invalid email or password.");
//        }
//    }
//
//    private void showMenu(Member member) {
//        Scanner scanner = new Scanner(System.in);
//        int option = -1;
//
//        while (option != 0) {
//            System.out.println("1. View all posts");
//            System.out.println("2. Create a post");
//            System.out.println("0. Exit");
//            option = scanner.nextInt();
//
//            switch (option) {
//                case 1:
//                    viewAllPosts();
//                    break;
//                case 2:
//                    createPost(member);
//                    break;
//                case 0:
//                    System.out.println("Exiting...");
//                    break;
//                default:
//                    System.out.println("Invalid option.");
//            }
//        }
//    }
//
//    private void viewAllPosts() {
//        List<Post> posts = postService.getAllPostsSortedByDate();
//        posts.forEach(post -> {
//            System.out.println(post.getContent() + " by " + post.getAuthorId());
//        });
//    }
//
//    private void createPost(Member member) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter content: ");
//        String content = scanner.nextLine();
//
//        Post post = new Post();
//        post.setAuthorId(member.getId());
//        post.setContent(content);
//        postService.createPost(post);
//
//        System.out.println("Post created successfully.");
//    }
//}
//
