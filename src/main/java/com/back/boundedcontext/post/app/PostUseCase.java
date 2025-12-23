package com.back.boundedcontext.post.app;

import com.back.boundedcontext.member.domain.Member;
import com.back.boundedcontext.post.domain.Post;
import com.back.boundedcontext.post.out.PostRepository;
import com.back.global.event.EventPublisher;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostUseCase {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;

    public Post write(Member author, String title, String content) {
        Post post = Post.create(author, title, content);
        Post savePost = postRepository.save(post);
        eventPublisher.publishEvent(new PostCreatedEvent(savePost.getId(), savePost.getAuthor().getId()));
        return savePost;
    }
}
