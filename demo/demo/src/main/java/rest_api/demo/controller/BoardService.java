package rest_api.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest_api.demo.controller.dto.BoardResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    // 전체 검색
    public List<Board> readAll() {
        return boardRepository.findAll();
    }

    @Transactional
    // 아이디 한개 검색
    public BoardResponseDto read_one(Long id) throws Exception{
         Board entity = boardRepository.findById(id)
                 .orElseThrow(()-> new IllegalAccessException("존재하지 않습니다."));
        return new BoardResponseDto(entity);
    }

    @Transactional
    public Long save()
}
