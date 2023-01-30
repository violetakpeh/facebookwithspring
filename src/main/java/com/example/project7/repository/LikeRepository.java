package com.example.project7.repository;

import com.example.project7.model.Post;
import com.example.project7.model.PostLikes;
import com.example.project7.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikeRepository extends JpaRepository<PostLikes, Long> {
    @Transactional
    void deletePostLikesByPostAndUser(Post post, User user);

    List<PostLikes> findAllByPost(Post post);

    void deleteAllByPost(Post post);
}
