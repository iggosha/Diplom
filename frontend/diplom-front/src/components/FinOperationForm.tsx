import React, { useState } from 'react';
import axios from 'axios';

const FinOperationForm: React.FC = () => {
  const [formData, setFormData] = useState({
    date: '',
    category: '',
    description: '',
    amount: '',
    status: ''
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await axios.post('/fin-ops/single', formData);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="date" name="date" value={formData.date} onChange={handleChange} />
      <input type="text" name="category" value={formData.category} onChange={handleChange} />
      <input type="text" name="description" value={formData.description} onChange={handleChange} />
      <input type="number" name="amount" value={formData.amount} onChange={handleChange} />
      <input type="text" name="status" value={formData.status} onChange={handleChange} />
      <button type="submit">Submit</button>
    </form>
  );
};

export default FinOperationForm;
