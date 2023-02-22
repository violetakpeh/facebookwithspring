package com.example.project7.serviceImplementation;

import com.example.project7.model.Comment;
import com.example.project7.model.Post;
import com.example.project7.model.User;
import com.example.project7.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CommentServiceImplTest {

    @Mock private CommentRepository commentRepository;
    private CommentServiceImpl commentServiceImpl;
    private AutoCloseable autoCloseable;
    private User user;
    private Comment comment;
    private Post post;
    @BeforeEach
    void setUp(){
        commentServiceImpl = new CommentServiceImpl(commentRepository);
        autoCloseable = MockitoAnnotations.openMocks(this);

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
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveComment() {
        commentServiceImpl.saveComment(comment);

        verify(commentRepository).save(comment);
    }

    @Test
    void findCommentByPost() {
        List<Comment> expected = commentServiceImpl.findCommentByPost(post);
        List<Comment> actual = commentRepository.findCommentByPost(post);
        assertEquals(expected,actual);
    }

    @Test
    void deleteComment() {
        commentServiceImpl.deleteComment(comment.getCommentId());
        verify(commentRepository).delete(comment);

    }

    @Test
    void deleteAllCommentsInPost() {
        commentServiceImpl.deleteAllCommentsInPost(post);
        verify(commentRepository).deleteAllByPost(post);

    }

    @Test
    void updateComment() {
        commentServiceImpl.updateComment(comment);
        verify(commentRepository).save(comment);
    }

    @Test
    void getCommentById() {
        Comment expected = commentServiceImpl.getCommentById(comment.getCommentId());
        Comment actual = commentRepository.findByCommentId(comment.getCommentId());
        assertEquals(expected,actual);
    }
}