import React, { useState } from 'react';
import axios from 'axios';
import '../mystyles.css'; // Импортируйте CSS-файл

const ManagePage: React.FC = () => {
  const [file, setFile] = useState<File | null>(null);
  const [formData, setFormData] = useState({
    date: '',
    category: '',
    description: '',
    amount: '',
    status: '',
  });
  const [deleteId, setDeleteId] = useState('');

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files.length > 0) {
      setFile(e.target.files[0]);
    }
  };

  const handleFileUpload = async () => {
    if (file) {
      const formData = new FormData();
      formData.append('file', file);
      try {
        const response = await axios.post('http://localhost:8080/api/fin-ops/file', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });
        alert('File uploaded and processed successfully');
        console.log('File upload response:', response.data);
      } catch (error) {
        console.error('Error uploading file:', error);
        alert('Error uploading file');
      }
    } else {
      console.error('No file selected');
      alert('No file selected');
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/fin-ops/single', formData);
      alert('Operation added successfully');
      console.log('Form submit response:', response.data);
    } catch (error) {
      console.error('Error submitting form:', error);
      alert('Error submitting form');
    }
  };

  const handleDeleteAll = async () => {
    try {
      await axios.delete('http://localhost:8080/api/fin-ops');
      alert('All operations deleted successfully');
      console.log('All operations deleted');
    } catch (error) {
      console.error('Error deleting all operations:', error);
      alert('Error deleting all operations');
    }
  };

  const handleDeleteById = async () => {
    try {
      await axios.delete(`http://localhost:8080/api/fin-ops/${deleteId}`);
      alert(`Operation with ID ${deleteId} deleted successfully`);
      console.log(`Operation with ID ${deleteId} deleted`);
    } catch (error) {
      console.error(`Error deleting operation with ID ${deleteId}:`, error);
      alert(`Error deleting operation with ID ${deleteId}`);
    }
  };

  return (
    <div className="container">
      <h1>Управление финансовыми операциями</h1>
      <div className="sections-container">
        <section className="section">
          <h2>Добавить операцию</h2>
          <div className="form-container">
            <form onSubmit={handleSubmit} className="form">
              <div className="form-group">
                <label htmlFor="date">Дата</label>
                <input type="date" name="date" value={formData.date} onChange={handleChange} />
              </div>
              <div className="form-group">
                <label htmlFor="category">Категория</label>
                <input type="text" name="category" value={formData.category} onChange={handleChange} />
              </div>
              <div className="form-group">
                <label htmlFor="description">Описание</label>
                <input type="text" name="description" value={formData.description} onChange={handleChange} />
              </div>
              <div className="form-group">
                <label htmlFor="amount">Сумма</label>
                <input type="number" name="amount" value={formData.amount} onChange={handleChange} />
              </div>
              <div className="form-group">
                <label htmlFor="status">Статус</label>
                <input type="text" name="status" value={formData.status} onChange={handleChange} />
              </div>
              <button type="submit" className="button">Подтвердить</button>
            </form>
          </div>
        </section>

        <section className="section">
          <h2>Добавить из файла</h2>
          <div className="file-upload-container">
            <input type="file" onChange={handleFileChange} />
            <button onClick={handleFileUpload} className="button">Загрузить</button>
          </div>
        </section>

        <section className="section">
          <h2>Удаление операций</h2>
          <div className="delete-container">
            <div className="form-group">
              <label htmlFor="deleteId">ID операции</label>
              <input type="text" name="deleteId" value={deleteId} onChange={(e) => setDeleteId(e.target.value)} />
            </div>
            <button onClick={handleDeleteById} className="button delete-button">Удалить по ID</button>
            <button onClick={handleDeleteAll} className="button delete-button">Удалить все</button>
          </div>
        </section>
      </div>
    </div>
  );
};

export default ManagePage;
