package com.example.minifacebookclone.repositories;

import com.example.minifacebookclone.entities.FacebookUser;
import com.example.minifacebookclone.entities.Likes;
import com.example.minifacebookclone.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    boolean existsByPostAndUser(Post post, FacebookUser currentUsername);
    Integer deleteByPostAndUser(Post post, FacebookUser currentUsername);
}
