package bsuedu.golovkov.fintracker.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FinOperationResponseDto {

    private String id;
    private LocalDate date;
    private LocalDate recordDate;
    private String category;
    private String description;
    private BigDecimal amount;
    private String status;
}
