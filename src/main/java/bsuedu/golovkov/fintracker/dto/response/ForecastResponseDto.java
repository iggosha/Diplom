package bsuedu.golovkov.fintracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastResponseDto {

    private YearMonth month;
    private BigDecimal totalAmount;
}
