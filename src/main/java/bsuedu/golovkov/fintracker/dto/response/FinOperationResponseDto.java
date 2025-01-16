package bsuedu.golovkov.fintracker.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class FinOperationResponseDto {

    private UUID id;
    private LocalDate date;
    private String status;
    private String category;
    private String description;

}
