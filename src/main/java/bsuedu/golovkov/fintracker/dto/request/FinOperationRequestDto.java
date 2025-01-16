package bsuedu.golovkov.fintracker.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class FinOperationRequestDto {

    private UUID id;
    private LocalDate date;
    private String status;
    private String category;
    private String description;
}
