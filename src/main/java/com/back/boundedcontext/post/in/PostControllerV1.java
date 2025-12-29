package com.back.boundedcontext.post.in;

import com.back.boundedcontext.post.app.PostFacade;
import com.back.shared.post.dto.PostDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post/posts")
public class PostControllerV1 {
    private final PostFacade postFacade;

    @GetMapping
    @Transactional(readOnly = true)
    public List<PostDto> getItems() {
        return postFacade
                .findByOrderByIdDesc()
                .stream()
                .map(PostDto::of)
                .toList();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public PostDto getItem(
            @PathVariable int id
    ) {
        return postFacade
                .findById(id)
                .map(PostDto::of)
                .get();
    }
}