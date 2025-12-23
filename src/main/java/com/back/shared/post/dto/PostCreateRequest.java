package com.back.shared.post.dto;

import com.back.boundedcontext.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class PostCreateRequest {
    private Member author;
    private String title;
    private String content;
}
