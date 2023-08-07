package com.example.minifacebookclone.services.servicesInterfaces;

import com.example.minifacebookclone.entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostServices {
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    Post createPost(Post post);
    Post updatePost(Post post);
    void deletePost(Long id);



}
