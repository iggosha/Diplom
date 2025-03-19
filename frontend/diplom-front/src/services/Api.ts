import axios from 'axios';

const API_URL = 'http://localhost:8080/api'; // Замените на ваш бэкенд URL

export const getFinOperations = () => {
  return axios.get(`${API_URL}/fin-ops`);
};

export const createFinOperation = (finOperation: any) => {
  return axios.post(`${API_URL}/fin-ops/single`, finOperation);
};

export const deleteFinOperation = (id: string) => {
  return axios.delete(`${API_URL}/fin-ops/${id}`);
};
