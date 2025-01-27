package bsuedu.golovkov.fintracker.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class RowResponseDto {

    private List<String> cells;

    public RowResponseDto(List<String> cells) {
        this.cells = cells;
    }
}
