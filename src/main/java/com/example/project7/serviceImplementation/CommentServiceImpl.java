package com.example.project7.serviceImplementation;

import com.example.project7.model.Comment;
import com.example.project7.model.Post;
import com.example.project7.repository.CommentRepository;
import com.example.project7.repository.PostRepository;
import com.example.project7.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
   private final CommentRepository commentRepository;

  //  private final PostRepository postRepository;

    @Override
    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findCommentByPost(Post post) {
        return commentRepository.findCommentByPost(post);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteCommentByCommentId(commentId);
    }

    @Override
    @Transactional
    public void deleteAllCommentsInPost(Post post) {
        commentRepository.deleteAllByPost(post);
    }

    @Override
    public void updateComment(Comment editedComment) {
        commentRepository.save(editedComment);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findByCommentId(id);
    }
}
