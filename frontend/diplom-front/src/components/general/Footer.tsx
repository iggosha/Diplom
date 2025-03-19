import React from 'react';

const Footer: React.FC = () => {
  return (
    <footer style={styles.footer}>
      <p style={styles.text}>© 2025 FinTracker</p>
    </footer>
  );
};

const styles = {
  footer: {
    backgroundColor: '#282c34',
    padding: '10px',
    color: 'white',
    textAlign: 'center' as const,
    position: 'fixed' as const,
    width: '100%',
    bottom: 0,
  },
  text: {
    margin: 0,
  },
};

export default Footer;
