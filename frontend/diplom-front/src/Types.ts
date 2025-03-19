export interface FinOperation {
  id: string;
  date: string;
  recordDate: string;
  category: string;
  description: string;
  amount: number;
  status: string;
}

export interface Page {
  size: number;
  number: number;
  totalElements: number;
  totalPages: number;
}

export interface FinOperationsResponse {
  content: FinOperation[];
  page: Page;
}

export interface FinOperationForecastResponseDto {
  month: string;
  totalAmount: number;
  type: string;
}