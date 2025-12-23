package com.back.boundedcontext.post.dto.request;

import com.back.boundedcontext.member.entity.Member;
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
