package rest_api.demo.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private Long id;
    private String title;
    private String content;

    @Builder
    public BoardSaveRequestDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


}
