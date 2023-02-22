package com.example.project7.controller;

import com.example.project7.model.Comment;
import com.example.project7.service.CommentService;
import com.example.project7.dto.ResponseDTO;
import com.example.project7.mapper.LikePosts;
import com.example.project7.model.Post;
import com.example.project7.service.PostService;
import com.example.project7.service.LikeService;
import com.example.project7.model.PostLikes;
import com.example.project7.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @GetMapping("/home")
    public String getHomePage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("logUser");
        if (user==null) return "redirect:/";
        model.addAttribute("post", new Post());
        model.addAttribute("newComment", new Comment());
        model.addAttribute("loggedUser", user);
        model.addAttribute("postLikes", new PostLikes());
        model.addAttribute("postDelete", new Post());

        List<LikePosts> listOfPosts = postService.getAllPost(user);

        model.addAttribute("listOfAllPosts", listOfPosts);
        return "home";
    }


    @GetMapping("/updatepost")
    public String getUpdatePostPage(Model model, HttpSession httpSession, Long postId) {
        User user = (User) httpSession.getAttribute("logUser");
        if (user==null) return "redirect:/";

        Post post = postService.getPostById(postId);

        model.addAttribute("editpost", post);
        model.addAttribute("loggedUser", user);
        System.out.println(user);

        return "updatepost";
    }


    @PostMapping("/home_post")
    public String createPost(HttpSession httpSession, @ModelAttribute("post") Post post) {
        User user = (User) httpSession.getAttribute("logUser");
        postService.addPost(user, post);
        return "redirect:/home";
    }


    @PostMapping("/update")
    public String updatePost(HttpSession httpSession, Post post){
        User user = (User) httpSession.getAttribute("logUser");
        if (user==null) return "redirect:/";

        Post newPost = postService.getPostById(post.getPostId());
        newPost.setBody(post.getBody());
        postService.updatePost(newPost);
        return "redirect:/home";
    }


    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        User user = (User) httpSession.getAttribute("logUser");
        ResponseDTO response = new ResponseDTO();
        if (user == null) {
            return "redirect:/index";
        }
        Post post = postService.getPostById(id);
        commentService.deleteAllCommentsInPost(post);
        likeService.deleteAllLikesInPost(post);
        postService.deletePost(post);
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        return "redirect:/home";
    }
}
