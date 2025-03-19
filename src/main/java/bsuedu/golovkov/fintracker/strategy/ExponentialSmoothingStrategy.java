package bsuedu.golovkov.fintracker.strategy;

import bsuedu.golovkov.fintracker.dto.response.FinOperationForecastResponseDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class ExponentialSmoothingStrategy implements ForecastStrategy {

    private static final BigDecimal ALPHA = BigDecimal.valueOf(0.5);
    private static final BigDecimal BETA = BigDecimal.valueOf(0.3);

    @Override
    public List<FinOperationForecastResponseDto> generateForecast(List<FinOperationForecastResponseDto> historicalData, int forecastMonths) {
        if (historicalData.isEmpty()) return new ArrayList<>();

        YearMonth lastMonth = historicalData.getLast().getMonth();
        BigDecimal level = historicalData.getFirst().getTotalAmount();
        BigDecimal trend = BigDecimal.ZERO;

        for (int i = 1; i < historicalData.size(); i++) {
            BigDecimal previousLevel = level;
            BigDecimal actual = historicalData.get(i).getTotalAmount();
            level = ALPHA.multiply(actual).add(BigDecimal.ONE.subtract(ALPHA).multiply(previousLevel));
            trend = BETA.multiply(level.subtract(previousLevel)).add(BigDecimal.ONE.subtract(BETA).multiply(trend));
        }

        List<FinOperationForecastResponseDto> forecast = new ArrayList<>();
        for (int i = 1; i <= forecastMonths; i++) {
            YearMonth forecastMonth = lastMonth.plusMonths(i);
            BigDecimal forecastValue = level.add(trend.multiply(BigDecimal.valueOf(i))).setScale(2, RoundingMode.HALF_UP);
            forecast.add(new FinOperationForecastResponseDto(forecastMonth, forecastValue));
        }
        return forecast;
    }
}
