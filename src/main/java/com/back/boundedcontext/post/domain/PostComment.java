package com.back.boundedcontext.post.domain;

import static jakarta.persistence.FetchType.LAZY;

import com.back.global.jpa.entity.BaseAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POST_POST_COMMENT")
@NoArgsConstructor
@Getter
public class PostComment extends BaseAndTime {
    @ManyToOne(fetch = LAZY)
    private Post post;

    @ManyToOne(fetch = LAZY)
    private PostMember author;

    @Column(columnDefinition = "TEXT")
    private String content;

    public PostComment(Post post, PostMember author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
    }
}