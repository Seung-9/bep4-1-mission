package com.back.boundedcontext.post.domain;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;

import com.back.boundedcontext.member.domain.Member;
import com.back.global.jpa.entity.BaseAndTime;
import com.back.shared.post.event.PostCommentCreatedEvent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POST_POST")
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class Post extends BaseAndTime {

    @ManyToOne(fetch = LAZY)
    private Member author;

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<PostComment> comments = new ArrayList<>();

    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    public PostComment addComment(Member author, String content) {
        PostComment postComment = PostComment.create(this, author, content);
        comments.add(postComment);
        publishEvent(new PostCommentCreatedEvent(postComment.getId(), postComment.getAuthor().getId()));
        return postComment;
    }

    public boolean hasComments() {
        return !comments.isEmpty();
    }

    public static Post create(Member author, String title, String content) {
        return Post.builder()
                .author(author)
                .title(title)
                .content(content)
                .build();
    }
}