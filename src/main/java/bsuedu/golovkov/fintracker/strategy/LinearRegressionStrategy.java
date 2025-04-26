package bsuedu.golovkov.fintracker.strategy;

import bsuedu.golovkov.fintracker.dto.response.ForecastResponseDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class LinearRegressionStrategy implements ForecastStrategy {

    @Override
    public List<ForecastResponseDto> generateForecast(List<ForecastResponseDto> historicalData, int forecastMonths) {
        if (historicalData.isEmpty()) return new ArrayList<>();

        int n = historicalData.size();
        YearMonth lastMonth = historicalData.get(n - 1).getMonth();
        BigDecimal sumX = BigDecimal.ZERO;
        BigDecimal sumY = BigDecimal.ZERO;
        BigDecimal sumXY = BigDecimal.ZERO;
        BigDecimal sumX2 = BigDecimal.ZERO;

        for (int i = 0; i < n; i++) {
            BigDecimal x = BigDecimal.valueOf(i + 1L);
            BigDecimal y = historicalData.get(i).getTotalAmount();
            sumX = sumX.add(x);
            sumY = sumY.add(y);
            sumXY = sumXY.add(x.multiply(y));
            sumX2 = sumX2.add(x.multiply(x));
        }

        BigDecimal meanX = sumX.divide(BigDecimal.valueOf(n), RoundingMode.HALF_UP);
        BigDecimal meanY = sumY.divide(BigDecimal.valueOf(n), RoundingMode.HALF_UP);
        BigDecimal numerator = sumXY.subtract(sumX.multiply(meanY));
        BigDecimal denominator = sumX2.subtract(sumX.multiply(meanX));
        BigDecimal slope = numerator.divide(denominator, RoundingMode.HALF_UP);
        BigDecimal intercept = meanY.subtract(slope.multiply(meanX));

        List<ForecastResponseDto> forecast = new ArrayList<>();
        for (int i = 1; i <= forecastMonths; i++) {
            YearMonth forecastMonth = lastMonth.plusMonths(i);
            BigDecimal x = BigDecimal.valueOf((long) n + i);
            BigDecimal forecastValue = slope.multiply(x).add(intercept).setScale(2, RoundingMode.HALF_UP);
            forecast.add(new ForecastResponseDto(forecastMonth, forecastValue));
        }
        return forecast;
    }
}
