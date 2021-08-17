package org.kenux.anything.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenux.anything.domain.entity.Post;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public PostsListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
        this.updatedDate = entity.getModifiedDate();
    }
}
