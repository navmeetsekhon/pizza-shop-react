import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const AdminMenuRow = ({ serialNo, name, category, price, soldLastWeek }) => {
  return (
    <tr>
      <td>{serialNo}</td>
      <td>{name}</td>
      <td>{category}</td>
      <td>{price}</td>
      <td>{soldLastWeek}</td>
      <td>
        <button className="btn btn-primary">Edit Item</button>
      </td>
    </tr>
  );
};

export default AdminMenuRow;
