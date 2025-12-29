package com.back.shared.post.dto;

import com.back.boundedcontext.post.domain.Post;
import java.time.LocalDateTime;

public record PostDto(
        int id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        int authorId,
        String authorName,
        String title,
        String content
) {
    public static PostDto of(Post post) {
        return new PostDto(
                post.getId(),
                post.getCreateDate(),
                post.getModifyDate(),
                post.getAuthor().getId(),
                post.getAuthor().getUsername(),
                post.getTitle(),
                post.getContent()
        );
    }
}