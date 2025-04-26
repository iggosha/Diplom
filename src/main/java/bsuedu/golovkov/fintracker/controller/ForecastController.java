package bsuedu.golovkov.fintracker.controller;

import bsuedu.golovkov.fintracker.dto.response.ForecastResponseDto;
import bsuedu.golovkov.fintracker.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fin-ops/forecast")
public class ForecastController {

    private final ForecastService forecastService;

    @GetMapping
    public List<ForecastResponseDto> getForecast(@RequestParam Integer monthNum,
                                                 @RequestParam(required = false, defaultValue = "EXP") String strategyName) {
        return forecastService.getMonthlyDataAndForecast(monthNum, strategyName);
    }
}
