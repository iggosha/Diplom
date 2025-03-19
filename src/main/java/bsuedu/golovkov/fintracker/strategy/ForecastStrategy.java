package bsuedu.golovkov.fintracker.strategy;

import bsuedu.golovkov.fintracker.dto.response.FinOperationForecastResponseDto;

import java.util.List;

public interface ForecastStrategy {

    List<FinOperationForecastResponseDto> generateForecast(List<FinOperationForecastResponseDto> historicalData, int forecastMonths);
}
