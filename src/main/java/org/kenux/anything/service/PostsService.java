package org.kenux.anything.service;

import lombok.RequiredArgsConstructor;
import org.kenux.anything.domain.dto.PostsListResponseDto;
import org.kenux.anything.repository.PostsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    public List<PostsListResponseDto> findAllPosts() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
