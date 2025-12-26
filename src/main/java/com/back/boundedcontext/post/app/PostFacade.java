package com.back.boundedcontext.post.app;

import com.back.boundedcontext.post.domain.Post;
import com.back.boundedcontext.post.domain.PostMember;
import com.back.global.rsdata.RsData;
import com.back.shared.member.dto.MemberDto;
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
    private final PostSupport postSupport;
    private final PostSyncMemberUseCase postSyncMemberUseCase;
    private final PostUseCase postUseCase;

    public long count() {
        return postSupport.count();
    }

    @Transactional
    public RsData<Post> write(PostCreateRequest request) {
        return postUseCase.write(request.getAuthor(), request.getTitle(), request.getContent());
    }

    public Optional<Post> findById(int id) {
        return postSupport.findById(id);
    }

    @Transactional
    public PostMember syncMember(MemberDto member) {
        return postSyncMemberUseCase.syncMember(member);
    }

    @Transactional(readOnly = true)
    public Optional<PostMember> findMemberByUsername(String username) {
        return postSupport.findMemberByUsername(username);
    }
}
