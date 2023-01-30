package com.example.project7.mapper;

import com.example.project7.model.Comment;
import com.example.project7.model.PostLikes;
import com.example.project7.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class LikePosts {
    private Long postId;
    private String title;
    private String body;
    private User user;
    private List<Comment> listOfComments = new ArrayList<>();
    private List<PostLikes> postLikes = new ArrayList<>();
    private boolean likedPost;

    public boolean getLikedPost() {
        return likedPost;
    }

    public void setLikedPost(boolean likedPost) {
        this.likedPost = likedPost;
    }
}
