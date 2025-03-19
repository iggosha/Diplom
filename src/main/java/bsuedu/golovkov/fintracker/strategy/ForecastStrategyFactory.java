package bsuedu.golovkov.fintracker.strategy;


public class ForecastStrategyFactory {

    public static ForecastStrategy getStrategy(String strategyType) {
        return switch (strategyType.toUpperCase()) {
            case "AVG" -> new AverageStrategy();
            case "LIN" -> new LinearRegressionStrategy();
            case "EXP" -> new ExponentialSmoothingStrategy();
            default -> throw new IllegalArgumentException("Неизвестная стратегия прогнозирования: " + strategyType);
        };
    }

    private ForecastStrategyFactory() {
    }
}
