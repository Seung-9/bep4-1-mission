package com.back.boundedcontext.post.app;

import com.back.shared.post.event.PostCreatedEvent;
import com.back.shared.post.dto.PostCreateRequest;
import com.back.boundedcontext.post.domain.Post;
import com.back.boundedcontext.post.out.PostRepository;
import com.back.global.event.EventPublisher;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;

    public long count() {
        return postRepository.count();
    }

    public Post write(PostCreateRequest request) {
        Post post = Post.create(request.getAuthor(), request.getTitle(), request.getContent());
        Post savePost = postRepository.save(post);
        eventPublisher.publishEvent(new PostCreatedEvent(savePost.getId(), savePost.getAuthor().getId()));
        return post;
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }
}