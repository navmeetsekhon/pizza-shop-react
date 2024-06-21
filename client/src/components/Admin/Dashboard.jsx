import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../../styles/Sidebar.css';
import employeeOfTheMonthImage from '../../helper/john_doe.jpeg';


// Sample data for additional cards
const additionalData = {
  employeeOfTheMonth: {
    name: 'John Doe',
    photoUrl: 'employee_photo_url.jpg'
  }
};

const Dashboard = ({ allOrders, pendingOrders,totalSale,cancelledOrders }) => {
  // console.log(allOrders);
  // console.log(pendingOrders);
  const createOrderCard = (title, count, borderColorClass) => {
    return (
      <div 
        className="col-lg-3 col-md-6 mb-4" 
        key={title}
        style={{ cursor: 'pointer' }}
      >
        <div className={`card border-${borderColorClass} shadow h-100 py-2 order-card`}>
          <div className="card-body">
            <h5 className="card-title">{title}</h5>
            <p className="card-text">{count}</p>
          </div>
        </div>
      </div>
    );
  };

  // Function to create additional cards
  const createAdditionalCard = (title, data, borderColorClass) => {
    return (
      <div 
        className="col-lg-3 col-md-6 mb-4" 
        key={title}
        style={{ cursor: 'pointer' }}
      >
        <div className={`card border-${borderColorClass} shadow h-100 py-2 order-card`}>
          <div className="card-body">
            <h5 className="card-title">{title}</h5>
            <p className="card-text">{data}</p>
          </div>
        </div>
      </div>
    );
  };

  return (
    <div className="row">
      {createOrderCard('Total Orders Today', (allOrders.length), 'primary')}
      {createOrderCard('Pending Orders', (pendingOrders.length), 'warning')}
      {createOrderCard('Completed Orders', (allOrders.length-pendingOrders.length), 'success')}
      {createOrderCard('Cancelled Orders', cancelledOrders.length, 'danger')}

      {/* Additional cards */}
      {createAdditionalCard('This Month\'s Net Sale', totalSale.toFixed(2), 'info')}
      {createAdditionalCard('This Month\'s Net Profit', ((totalSale/100)*10).toFixed(2), 'secondary')}
      {createAdditionalCard('Orders This Month', allOrders.length*30, 'dark')}
      <div className="col-lg-3 col-md-6 mb-4 d-flex align-items-center justify-content-center">
        <div className="card border-primary shadow h-100 py-2 order-card">
          <div className="card-body text-center">
            <h5 className="card-title">Employee of the Month</h5>
            <p className="card-text">{additionalData.employeeOfTheMonth.name}</p>
            <img src={employeeOfTheMonthImage} alt="Employee of the Month" className="img-fluid" style={{ maxHeight: '200px' }} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
