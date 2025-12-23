package com.back.boundedcontext.member.domain;

import com.back.global.jpa.entity.BaseAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Member extends BaseAndTime {

    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    private int activityScore;

    public int increaseActivityScore(int amount) {
        return this.activityScore += amount;
    }

    public static Member create(String username, String password, String nickname) {
        return Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
    }
}