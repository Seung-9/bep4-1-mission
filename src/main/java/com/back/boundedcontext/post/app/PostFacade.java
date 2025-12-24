package com.back.boundedcontext.post.app;

import com.back.boundedcontext.post.domain.Post;
import com.back.boundedcontext.post.out.PostRepository;
import com.back.global.rsdata.RsData;
import com.back.shared.post.dto.PostCreateRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PostFacade {
    private final PostRepository postRepository;
    private final PostUseCase postUseCase;

    public long count() {
        return postRepository.count();
    }

    @Transactional
    public RsData<Post> write(PostCreateRequest request) {
        return postUseCase.write(request.getAuthor(), request.getTitle(), request.getContent());
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }
}
