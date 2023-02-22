package com.example.project7.serviceImplementation;

import com.example.project7.model.Comment;
import com.example.project7.model.Post;
import com.example.project7.model.User;
import com.example.project7.repository.CommentRepository;
import com.example.project7.repository.LikeRepository;
import com.example.project7.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private LikeRepository likeRepository;
    @InjectMocks
    private PostServiceImpl postServiceImpl;
    //private AutoCloseable autoCloseable;
    private User user;
    private Comment comment;
    private Post post;
    @BeforeEach
    void setUp(){
        postServiceImpl= new PostServiceImpl(postRepository,likeRepository,commentRepository);
       // autoCloseable = MockitoAnnotations.openMocks(this);

        user = new User();
        user.setFirstName("Victor");
        user.setLastName("Daniels");
        user.setGender("male");
        user.setPassword("12345");
        user.setEmailAddress("victordaniels@yahoo.com");

        post =new Post();
        post.setPostId(1L);
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
//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }

    @Test
    void addPost() {
        postServiceImpl.addPost(user,post);
        verify(postRepository).save(post);
    }

    @Test
    void getAllPost() {
       when(postRepository.findAll()).thenReturn(List.of(post));
        postServiceImpl.getAllPost(user);

        //verify(postRepository).findAll();
    }

    @Test
    void updatePost() {
        postServiceImpl.updatePost(post);
        verify(postRepository).save(post);
    }

    @Test
    void deletePost() {
        postServiceImpl.deletePost(post);
        verify(postRepository).delete(post);
    }

    @Test
    void getPostById() {
        postServiceImpl.getPostById(post.getPostId());
        verify(postRepository).findById(post.getPostId());
    }
}