import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from 'chart.js';
import '../mystyles.css';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

interface FinOperationForecastResponseDto {
  month: string;
  totalAmount: number;
}

const Forecast: React.FC = () => {
  const [chartData, setChartData] = useState<FinOperationForecastResponseDto[]>([]);
  const [monthNum, setMonthNum] = useState<number>(3);

  useEffect(() => {
    const fetchForecastData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/fin-ops/forecast?monthNum=${monthNum}`);
        setChartData(response.data);
      } catch (error) {
        console.error('Error fetching forecast data:', error);
      }
    };

    fetchForecastData();
  }, [monthNum]);

  // Функция для определения цвета
  const getBarColor = (amount: number, index: number) => {
    if (index >= chartData.length - monthNum) return '#FFFFFF'; // Прогнозные значения — белые
    return amount >= 0 ? 'rgb(0, 255, 0)' : 'rgb(255, 0, 0)'; // Прибыль — зелёный, убыток — красный
  };

  const data = {
    labels: chartData.map(item => item.month),
    datasets: [
      {
        label: 'Сумма',
        data: chartData.map(item => item.totalAmount), // Итоговые суммы!
        backgroundColor: chartData.map((item, index) => getBarColor(item.totalAmount, index)),
        borderWidth: 1,
      },
    ],
  };

  const options = {
    responsive: true,
    plugins: {
      legend: { position: 'top' as const },
      title: { display: true, text: 'Прогноз изменений баланса' },
    },
    scales: {
      y: { beginAtZero: true },
    },
  };

  return (
    <div className="container">
      <h1>Раздел прогнозирования</h1>
      <div className="chart-container">
        <Bar data={data} options={options} />
      </div>
      <div className="form-group">
        <label htmlFor="monthNum">Количество месяцев</label>
        <input
          type="number"
          id="monthNum"
          value={monthNum}
          onChange={(e) => setMonthNum(Number(e.target.value))}
        />
      </div>
    </div>
  );
};

export default Forecast;
