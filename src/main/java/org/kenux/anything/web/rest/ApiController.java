package org.kenux.anything.web.rest;

import lombok.RequiredArgsConstructor;
import org.kenux.anything.web.dto.PostsListResponseDto;
import org.kenux.anything.domain.entity.Post;
import org.kenux.anything.repository.PostsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApiController {

    private final PostsRepository postsRepository;

    @PostMapping("/posts")
    public ResponseEntity<?> registerPosts(@RequestBody PostsListResponseDto posts) {
        Post post = Post.builder()
                .author(posts.getAuthor())
                .content(posts.getContent())
                .title(posts.getTitle())
                .build();
        Post save = postsRepository.save(post);

        return ResponseEntity.ok("게시글이 저장되었습니다. - " + save.getId());

    }
}
