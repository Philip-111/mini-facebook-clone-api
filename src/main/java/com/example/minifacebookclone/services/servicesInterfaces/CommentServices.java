package com.example.minifacebookclone.services.servicesInterfaces;


import com.example.minifacebookclone.entities.Commentfac;
import com.example.minifacebookclone.entities.FacebookUser;

import java.util.List;

public interface CommentServices {

    Commentfac createComment(Long postId, String commentText, FacebookUser user);
    List<Commentfac> getCommentsByPostId(Long postId);
}
