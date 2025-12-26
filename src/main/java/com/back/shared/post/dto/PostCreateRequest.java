package com.back.shared.post.dto;

import com.back.boundedcontext.post.domain.PostMember;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class PostCreateRequest {
    private PostMember author;
    private String title;
    private String content;
}
