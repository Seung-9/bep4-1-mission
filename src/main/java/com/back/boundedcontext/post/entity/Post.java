package com.back.boundedcontext.post.entity;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;

import com.back.boundedcontext.member.entity.Member;
import com.back.global.jpa.entity.BaseAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Post extends BaseAndTime {

    @ManyToOne(fetch = LAZY)
    private Member author;

    @OneToMany(mappedBy = "post", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<PostComment> comments = new ArrayList<>();

    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    public Post(Member author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public PostComment addComment(Member author, String content) {
        PostComment postComment = new PostComment(this, author, content);

        comments.add(postComment);
        author.increaseActivityScore(1); // 댓글 작성시 활동점수 1점 증가

        return postComment;
    }

    public boolean hasComments() {
        return !comments.isEmpty();
    }
}