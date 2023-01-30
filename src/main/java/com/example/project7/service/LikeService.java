package com.example.project7.service;

import com.example.project7.model.Post;
import com.example.project7.model.User;

public interface LikeService {
    boolean likePost(User user, Long postId, String action);
    void deleteAllLikesInPost(Post post);
}
