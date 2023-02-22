package com.example.project7.repository;

import com.example.project7.model.Comment;
import com.example.project7.model.Post;
import com.example.project7.model.User;
import com.example.project7.serviceImplementation.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    private Comment comment;
    private User user;
    private Post post;

    @BeforeEach
    void setUp(){
//        commentServiceImpl = new CommentServiceImpl(commentRepository);
//        autoCloseable = MockitoAnnotations.openMocks(this);

        user = new User();
        user.setFirstName("Victor");
        user.setLastName("Daniels");
        user.setGender("male");
        user.setPassword("12345");
        user.setEmailAddress("victordaniels@yahoo.com");

        post =new Post();
        post.setBody("This is a nice Post");
        post.setTitle("title");
        post.setUser(user);

        comment = new Comment();
        comment.setCommentId(1L);
        comment.setComments("This is a really good comment");
        comment.setCommentBody("Body of the comment");
        comment.setPost(post);
        comment.setUser(user);



    }

    @Test
    void findByCommentId() {
        Comment comment1 = commentRepository.findByCommentId(comment.getCommentId());
        assertEquals(comment1.getCommentId(), comment.getCommentId());

    }

    @Test
    void findCommentByPost() {
        List<Comment> commentList = commentRepository.findCommentByPost(post);
        commentList.add(comment);
        comment.getPost();

        assertEquals(commentList.get(0).getPost(), post);
    }

    @Test
    void deleteCommentByCommentId() {
        commentRepository.deleteCommentByCommentId(comment.getCommentId());
        Comment comment1 = commentRepository.findByCommentId(comment.getCommentId());
        assertNull(comment1);
    }

    @Test
    void deleteAllByPost() {
        commentRepository.deleteAllByPost(post);
        List<Comment> commentList = commentRepository.findCommentByPost(post);
        assertEquals(commentList.size(), 0);
    }

}