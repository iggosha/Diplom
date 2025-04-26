package bsuedu.golovkov.fintracker.strategy;

import bsuedu.golovkov.fintracker.dto.response.ForecastResponseDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class AverageStrategy implements ForecastStrategy {

    @Override
    public List<ForecastResponseDto> generateForecast(List<ForecastResponseDto> historicalData, int forecastMonths) {
        if (historicalData.isEmpty()) return new ArrayList<>();

        BigDecimal totalSum = historicalData.stream()
                .map(ForecastResponseDto::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal average = totalSum.divide(BigDecimal.valueOf(historicalData.size()), RoundingMode.HALF_UP);

        List<ForecastResponseDto> forecast = new ArrayList<>();
        YearMonth lastMonth = historicalData.getLast().getMonth();
        for (int i = 1; i <= forecastMonths; i++) {
            YearMonth forecastMonth = lastMonth.plusMonths(i);
            forecast.add(new ForecastResponseDto(forecastMonth, average));
        }
        return forecast;
    }
}
