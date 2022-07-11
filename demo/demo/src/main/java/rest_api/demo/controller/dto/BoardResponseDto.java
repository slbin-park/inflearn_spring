package rest_api.demo.controller.dto;


import lombok.Getter;
import rest_api.demo.controller.Board;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
