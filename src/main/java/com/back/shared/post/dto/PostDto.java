package com.back.shared.post.dto;

import com.back.boundedcontext.post.domain.Post;
import com.back.standard.modeltype.HasModelTypeCode;
import java.time.LocalDateTime;

public record PostDto(
        int id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        int authorId,
        String authorName,
        String title,
        String content
) implements HasModelTypeCode {
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

    @Override
    public String getModelTypeCode() {
        return "Post";
    }
}