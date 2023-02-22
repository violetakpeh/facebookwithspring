package com.example.project7.repository;

import com.example.project7.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByPostIdIsNotNullOrderByPostIdDesc();
//    @Query("select p from posts p " +
//            "join fetch p.listOfComments " +
//            "where p.postId = ?1")
    Post findByPostId(Long id);
}
