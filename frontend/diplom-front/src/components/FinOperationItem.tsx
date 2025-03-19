import React from 'react';
import { FinOperation, } from  '../Types.ts'

interface FinOperationItemProps {
  finOperation: FinOperation;
}

const FinOperationItem: React.FC<FinOperationItemProps> = ({ finOperation }) => {
  return (
    <tr>
      <td style={styles.td}>{finOperation.id}</td>
      <td style={styles.td}>{finOperation.date}</td>
      <td style={styles.td}>{finOperation.recordDate}</td>
      <td style={styles.td}>{finOperation.category}</td>
      <td style={styles.td}>{finOperation.description}</td>
      <td style={styles.td}>{finOperation.amount}</td>
      <td style={styles.td}>{finOperation.status}</td>
    </tr>
  );
};

const styles = {
  td: {
    border: '1px solid #ddd',
    padding: '12px',
  },
};

export default FinOperationItem;
