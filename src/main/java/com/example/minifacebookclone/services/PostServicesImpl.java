package com.example.minifacebookclone.services;
import com.example.minifacebookclone.entities.Post;
import com.example.minifacebookclone.repositories.PostRepository;
import com.example.minifacebookclone.repositories.UserRepository;
import com.example.minifacebookclone.services.servicesInterfaces.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class PostServicesImpl implements PostServices {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired

    public PostServicesImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        Optional<Post> postToUpdate = postRepository.findById(post.getId());
        if (postToUpdate.isPresent()) {
            postToUpdate.get().setContent(post.getContent());
            postRepository.save(postToUpdate.get());
        }
        return post;
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
