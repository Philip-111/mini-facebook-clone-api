package com.example.minifacebookclone.controller;

import com.example.minifacebookclone.services.servicesInterfaces.LikesServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/likes")
public class LikeController {
    private final LikesServices likesService;

    @Autowired
    public LikeController(LikesServices likesService) {
        this.likesService = likesService;
    }

    @PostMapping("/increment/{postId}")
    public String incrementLikeCount(@PathVariable("postId") Long postId, HttpServletRequest request) {
        likesService.toggleLike(postId, request);
        return "redirect:/home";
    }
    @DeleteMapping("/{likeId}")
    public String deleteLike(@PathVariable("likeId") Long likeId) {
        likesService.deleteLike(likeId);
        return "redirect:/home";
    }
}
