package org.kenux.anything.controller.rest;

import lombok.RequiredArgsConstructor;
import org.kenux.anything.domain.dto.PostsDto;
import org.kenux.anything.domain.entity.Post;
import org.kenux.anything.repository.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApiController {

    private final PostRepository postRepository;

    @PostMapping("/posts")
    public ResponseEntity<?> registerPosts(@RequestBody PostsDto posts) {
        Post post = Post.builder()
                .author(posts.getAuthor())
                .content(posts.getContent())
                .title(posts.getTitle())
                .build();
        Post save = postRepository.save(post);

        return ResponseEntity.ok("게시글이 저장되었습니다. - " + save.getId());

    }
}
