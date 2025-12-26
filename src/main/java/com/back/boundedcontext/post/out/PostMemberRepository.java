package com.back.boundedcontext.post.out;

import com.back.boundedcontext.post.domain.PostMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMemberRepository extends JpaRepository<PostMember, Long> {
    Optional<PostMember> findByUsername(String username);
}
