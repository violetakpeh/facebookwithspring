package com.example.project7.serviceImplementation;

import com.example.project7.model.Comment;
import com.example.project7.model.Post;
import com.example.project7.model.PostLikes;
import com.example.project7.model.User;
import com.example.project7.repository.CommentRepository;
import com.example.project7.repository.LikeRepository;
import com.example.project7.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class LikeServiceImplTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private LikeRepository likeRepository;
    private LikeServiceImpl likeServiceImpl;
    private AutoCloseable autoCloseable;
    private User user;
    private Comment comment;
    private Post post;
    private PostLikes postLikes;
    @BeforeEach
    void setUp(){
        likeServiceImpl= new LikeServiceImpl(likeRepository,postRepository);
        autoCloseable = MockitoAnnotations.openMocks(this);

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

        postLikes = new PostLikes();
        postLikes.setPost(post);
        postLikes.setUser(user);
        postLikes.setPostLikeId(1L);



    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void likePost() {
        likeServiceImpl.likePost(user,post.getPostId(),"1");
    verify(likeRepository).save(postLikes);
    }




    @Test
    void deleteAllLikesInPost() {
       likeServiceImpl.deleteAllLikesInPost(post);
        verify(likeRepository).deleteAllByPost(post);
    }
}