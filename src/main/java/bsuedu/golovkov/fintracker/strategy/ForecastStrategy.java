package bsuedu.golovkov.fintracker.strategy;

import bsuedu.golovkov.fintracker.dto.response.ForecastResponseDto;

import java.util.List;

public interface ForecastStrategy {

    List<ForecastResponseDto> generateForecast(List<ForecastResponseDto> historicalData, int forecastMonths);
}
