package com.example.project7.service;

import com.example.project7.mapper.LikePosts;
import com.example.project7.model.Post;
import com.example.project7.model.User;

import java.util.List;

public interface PostService {
    void addPost(User user, Post post);

    List<LikePosts> getAllPost(User user);

    void updatePost(Post post);

    void deletePost(Post post);

    Post getPostById(Long id);
}
