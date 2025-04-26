package bsuedu.golovkov.fintracker.service.impl;

import bsuedu.golovkov.fintracker.dto.response.ForecastResponseDto;
import bsuedu.golovkov.fintracker.entity.FinOperation;
import bsuedu.golovkov.fintracker.repository.FinOperationRepository;
import bsuedu.golovkov.fintracker.service.ForecastService;
import bsuedu.golovkov.fintracker.strategy.ForecastStrategy;
import bsuedu.golovkov.fintracker.strategy.ForecastStrategyFactory;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ForecastServiceImpl implements ForecastService {

    private final FinOperationRepository finOperationRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ForecastResponseDto> getMonthlyDataAndForecast(Integer forecastMonths, String strategyName) {
        if (forecastMonths < 1) {
            throw new ValidationException("Forecast month amount must be positive");
        }
        ForecastStrategy strategy = ForecastStrategyFactory.getStrategy(strategyName);
        List<ForecastResponseDto> historicalData = calculateMonthlyTotals(groupOperationsByMonth());
        historicalData.sort(Comparator.comparing(ForecastResponseDto::getMonth));
        List<ForecastResponseDto> forecastData = strategy.generateForecast(historicalData, forecastMonths);
        historicalData.addAll(forecastData);
        return historicalData;
    }

    private Map<YearMonth, List<FinOperation>> groupOperationsByMonth() {
        return finOperationRepository.findAll()
                .stream()
                .filter(operation -> operation.getDate() != null)
                .collect(Collectors.groupingBy(operation -> YearMonth.from(operation.getDate())));
    }

    private List<ForecastResponseDto> calculateMonthlyTotals(Map<YearMonth, List<FinOperation>> operationsByMonth) {
        return operationsByMonth.entrySet().stream()
                .map(entry -> new ForecastResponseDto(
                        entry.getKey(),
                        entry.getValue().stream()
                                .map(FinOperation::getAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                ))
                .collect(Collectors.toList());
    }
}
