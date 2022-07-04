package rest_example.rest.model.request;

import lombok.Data;

import java.util.List;

@Data
public class BookLendRequest {
    private List<Long> bookIds;
    private Long memberId;
}
