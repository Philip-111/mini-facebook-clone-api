package com.example.minifacebookclone.services.servicesInterfaces;

import jakarta.servlet.http.HttpServletRequest;

public interface LikesServices {
    void toggleLike(Long postId, HttpServletRequest request);
    void deleteLike(Long likeId);
}
