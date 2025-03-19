package bsuedu.golovkov.fintracker.strategy;

import bsuedu.golovkov.fintracker.dto.response.FinOperationForecastResponseDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class AverageStrategy implements ForecastStrategy {

    @Override
    public List<FinOperationForecastResponseDto> generateForecast(List<FinOperationForecastResponseDto> historicalData, int forecastMonths) {
        if (historicalData.isEmpty()) return new ArrayList<>();

        BigDecimal totalSum = historicalData.stream()
                .map(FinOperationForecastResponseDto::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal average = totalSum.divide(BigDecimal.valueOf(historicalData.size()), RoundingMode.HALF_UP);

        List<FinOperationForecastResponseDto> forecast = new ArrayList<>();
        YearMonth lastMonth = historicalData.getLast().getMonth();
        for (int i = 1; i <= forecastMonths; i++) {
            YearMonth forecastMonth = lastMonth.plusMonths(i);
            forecast.add(new FinOperationForecastResponseDto(forecastMonth, average));
        }
        return forecast;
    }
}
