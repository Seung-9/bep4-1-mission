package com.back.boundedcontext.post.app;

import com.back.boundedcontext.member.domain.Member;
import com.back.boundedcontext.post.domain.Post;
import com.back.boundedcontext.post.out.PostRepository;
import com.back.global.event.EventPublisher;
import com.back.global.rsdata.RsData;
import com.back.shared.member.out.MemberApiClient;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostUseCase {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;
    private final MemberApiClient memberApiClient;

    public RsData<Post> write(Member author, String title, String content) {
        Post post = Post.create(author, title, content);
        Post savePost = postRepository.save(post);
        eventPublisher.publishEvent(new PostCreatedEvent(savePost.getId(), savePost.getAuthor().getId()));
        String randomSecureTip = memberApiClient.getRandomSecureTip();
        return new RsData<>("201-1", "%d번 글이 생성되었습니다. 보안 팁 : %s".formatted(savePost.getId(), randomSecureTip), savePost);
    }
}
