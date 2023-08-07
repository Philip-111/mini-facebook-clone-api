package com.example.minifacebookclone.services;
import com.example.minifacebookclone.entities.FacebookUser;
import com.example.minifacebookclone.entities.Likes;
import com.example.minifacebookclone.entities.Post;
import com.example.minifacebookclone.exceptions.LikeNotFoundException;
import com.example.minifacebookclone.exceptions.PostNotFoundException;
import com.example.minifacebookclone.repositories.LikesRepository;
import com.example.minifacebookclone.repositories.PostRepository;
import com.example.minifacebookclone.services.servicesInterfaces.LikesServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class LikesServicesImpl implements LikesServices {
    private final LikesRepository likesRepository;
    private final PostRepository postRepository;

    public LikesServicesImpl(LikesRepository likesRepository, PostRepository postRepository) {
        this.likesRepository = likesRepository;
        this.postRepository = postRepository;
    }

    public void toggleLike(Long postId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        FacebookUser currentUser = (FacebookUser)  session.getAttribute("user");
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            boolean hasLiked = likesRepository.existsByPostAndUser(post, currentUser);

            if (hasLiked) {

                post.setLikeCount(post.getLikeCount() - 1);
                likesRepository.deleteByPostAndUser(post, currentUser);
            } else {

                post.setLikeCount(post.getLikeCount() + 1);
                Likes like = new Likes();
                like.setPost(post);
                like.setUser(currentUser);
                likesRepository.save(like);
            }

            postRepository.save(post);
        } else {
            throw new PostNotFoundException("Post not found with ID: " + postId);
        }
    }
    public void deleteLike(Long likeId) {
        Optional<Likes> optionalLike = likesRepository.findById(likeId);
        if (optionalLike.isPresent()) {
            Likes like = optionalLike.get();
            likesRepository.delete(like);
            Post post = like.getPost();
            post.setLikeCount(post.getLikeCount() - 1);
            postRepository.save(post);
        } else {
            throw new LikeNotFoundException("Like not found with ID: " + likeId);
        }
    }

}
