package com.example.minifacebookclone.services;

import com.example.minifacebookclone.entities.Commentfac;
import com.example.minifacebookclone.entities.FacebookUser;
import com.example.minifacebookclone.entities.Post;
import com.example.minifacebookclone.repositories.CommentRepository;
import com.example.minifacebookclone.repositories.PostRepository;
import com.example.minifacebookclone.services.servicesInterfaces.CommentServices;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class CommentServicesImpl implements CommentServices {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServicesImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Commentfac createComment(Long postId, String commentText, FacebookUser user) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Commentfac comment = new Commentfac();
        comment.setText(commentText);
        comment.setTimestamp(LocalDateTime.now());
        comment.setUser(user);
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    @Override
    public List<Commentfac> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

}
