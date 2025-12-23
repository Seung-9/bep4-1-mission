package com.back.boundedcontext.post.entity;

import static jakarta.persistence.FetchType.LAZY;

import com.back.boundedcontext.member.entity.Member;
import com.back.global.jpa.entity.BaseAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class PostComment extends BaseAndTime {
    @ManyToOne(fetch = LAZY)
    private Post post;

    @ManyToOne(fetch = LAZY)
    private Member author;

    @Column(columnDefinition = "TEXT")
    private String content;

    public PostComment(Post post, Member author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
    }
}