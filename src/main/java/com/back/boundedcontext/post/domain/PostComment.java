package com.back.boundedcontext.post.domain;

import static jakarta.persistence.FetchType.LAZY;

import com.back.boundedcontext.member.domain.Member;
import com.back.global.jpa.entity.BaseAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class PostComment extends BaseAndTime {
    @ManyToOne(fetch = LAZY)
    private Post post;

    @ManyToOne(fetch = LAZY)
    private Member author;

    @Column(columnDefinition = "TEXT")
    private String content;

    public static PostComment create(Post post, Member author, String content) {
        return PostComment.builder()
                .post(post)
                .author(author)
                .content(content)
                .build();
    }
}