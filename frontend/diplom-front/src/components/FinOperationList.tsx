import React, { useEffect, useState } from 'react';
import axios from 'axios';
import FinOperationItem from './FinOperationItem';
import { FinOperationsResponse, FinOperation, Page } from '../Types.ts';
import '../mystyles.css';

const FinOperationList: React.FC = () => {
  const [finOperations, setFinOperations] = useState<FinOperation[]>([]);
  const [page, setPage] = useState<Page>({ size: 0, number: 0, totalElements: 0, totalPages: 0 });
  const [sortField, setSortField] = useState<string>('date');
  const [sortDirection, setSortDirection] = useState<string>('desc');
  const [error, setError] = useState<string | null>(null);

  const fetchFinOperations = async (pageNumber: number) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/fin-ops/page?page=${pageNumber}&size=10&sort=${sortField},${sortDirection}`);
      const data: FinOperationsResponse = response.data;
      setFinOperations(data.content);
      setPage(data.page);
    } catch (error) {
      console.error(error);
      setError('Ошибка получения списка операций');
    }
  };

  useEffect(() => {
    fetchFinOperations(0);
  }, [sortField, sortDirection]);

  const handlePageChange = (newPage: number) => {
    fetchFinOperations(newPage);
  };

  const handleSort = (field: string) => {
    const direction = sortField === field && sortDirection === 'asc' ? 'desc' : 'asc';
    setSortField(field);
    setSortDirection(direction);
  };

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
      <div className="container">
        <table className="table">
          <thead>
          <tr>
            <th onClick={() => handleSort('id')}>ID</th>
            <th onClick={() => handleSort('date')}>Дата</th>
            <th onClick={() => handleSort('recordDate')}>Дата проводки</th>
            <th onClick={() => handleSort('category')}>Категория</th>
            <th onClick={() => handleSort('description')}>Описание</th>
            <th onClick={() => handleSort('amount')}>Сумма</th>
            <th onClick={() => handleSort('status')}>Статус</th>
          </tr>
          </thead>
          <tbody>
          {finOperations.map((finOperation) => (
              <FinOperationItem key={finOperation.id} finOperation={finOperation} />
          ))}
          </tbody>
        </table>
        <div className="pagination">
          <button
              disabled={page.number === 0}
              onClick={() => handlePageChange(page.number - 1)}
              className="button"
          >
            Previous
          </button>
          <span>{page.number + 1} of {page.totalPages}</span>
          <button
              disabled={page.number === page.totalPages - 1}
              onClick={() => handlePageChange(page.number + 1)}
              className="button"
          >
            Next
          </button>
        </div>
      </div>
  );
};

export default FinOperationList;
