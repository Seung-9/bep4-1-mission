package com.back.boundedcontext.post.domain;

import com.back.global.jpa.entity.BaseAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POST_MEMBER")
@NoArgsConstructor
@Getter
public class PostMember extends BaseAndTime {
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    private int activityScore;
}