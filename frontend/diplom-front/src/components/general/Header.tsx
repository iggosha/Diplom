import React from 'react';
import { Link } from 'react-router-dom';

const Header: React.FC = () => {
  return (
    <header style={styles.header}>
      <h1 style={styles.title}>FinTracker</h1>
      <nav style={styles.nav}>
        <Link to="/" style={styles.link}>Главная</Link>
        <Link to="/manage" style={styles.link}>Управление</Link>
        <Link to="/forecast" style={styles.link}>Прогнозирование</Link>
      </nav>
    </header>
  );
};

const styles = {
  header: {
    backgroundColor: '#282c34',
    padding: '20px',
    color: 'white',
    textAlign: 'center' as const,
    position: 'fixed' as const,
    width: '100%',
    top: 0,
    zIndex: 1000,
  },
  title: {
    margin: 0,
  },
  nav: {
    marginTop: '10px',
  },
  link: {
    color: 'white',
    margin: '0 15px',
    textDecoration: 'none',
  },
};

export default Header;
