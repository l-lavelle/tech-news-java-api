package com.technews.repository;

import com.technews.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
public interface CommentRepository extends JpaRepository<Comment, Integer>{
    List<Comment> findAllCommentsByPostId(int postId);
}
