import React from 'react';
import FinOperationList from '../components/FinOperationList';

const HomePage: React.FC = () => {
  return (
    <div style={styles.container}>
      <h1>Список операций</h1>
      <FinOperationList />
    </div>
  );
};

const styles = {
  container: {
    padding: '100px',
  },
};

export default HomePage;
