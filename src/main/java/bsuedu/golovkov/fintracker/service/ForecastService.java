package bsuedu.golovkov.fintracker.service;

import bsuedu.golovkov.fintracker.dto.response.ForecastResponseDto;

import java.util.List;

public interface ForecastService {

    List<ForecastResponseDto> getMonthlyDataAndForecast(Integer forecastMonths, String strategyName);
}
