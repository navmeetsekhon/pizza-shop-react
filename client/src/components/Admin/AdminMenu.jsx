import React from "react";
import AdminMenuRow from "./AdminMenuRow";
import "bootstrap/dist/css/bootstrap.min.css";
import "../../styles/AdminMenu.css";

function getRandomObjectFromArray(arr) {
  if (arr.length === 0) {
    return null;
  }
  const randomIndex = Math.floor(Math.random() * arr.length);
  return arr[randomIndex];
}



const AdminMenu = ({ menu }) => {
  const mostSellingItem = getRandomObjectFromArray(menu);
  const leastSellingItem = getRandomObjectFromArray(menu);
  const mostProfitableItem = getRandomObjectFromArray(menu);
  const leastProfitableItem = getRandomObjectFromArray(menu);
  const mostExpensiveItem = getRandomObjectFromArray(menu);

  const createCard = (title, item, borderColorClass) => {
    return (
      <div className="col-lg-3 col-md-6 mb-4" key={title}>
        <div className={`card border-${borderColorClass} shadow h-100 py-2`}>
          <div className="card-body">
            <h5 className="card-title">{title}</h5>
            <p className="card-text">{item.itemName}</p>
            <p className="card-text">{`Price: ${(item.itemPrice).toFixed(2)}`}</p>
            <p className="card-text">{`Sold Last Week: ${10}`}</p>
            <p className="card-text">{`Profit: $${
              100
            }`}</p>
          </div>
        </div>
      </div>
    );
  };
  const categories = [...new Set(menu.map((item) => item.itemCategory))];

  return (
    <div className="container mt-5">
      <h2>Menu Insights</h2>
      <div className="row">
        {createCard("Most Selling Item", mostSellingItem, "primary")}
        {createCard("Least Selling Item", leastSellingItem, "warning")}
        {createCard("Most Profitable Item", mostProfitableItem, "success")}
        {createCard("Least Profitable Item", leastProfitableItem, "danger")}
        {createCard("Most Expensive Item", mostExpensiveItem, "info")}
      </div>
      <h2>Menu Items</h2>
      {categories.map((category) => (
        <div key={category}>
          <h3>{category}</h3>
          <table className="table table-bordered">
            <thead>
              <tr>
                <th>Serial No.</th>
                <th>Item Name</th>
                <th>Item Category</th>
                <th>Item Price</th>
                <th>Sold Last Week</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {menu
                .filter((item) => item.itemCategory === category)
                .map((item, index) => (
                  <AdminMenuRow
                    key={item.id}
                    serialNo={index + 1}
                    name={item.itemName}
                    category={item.itemCategory}
                    price={item.itemPrice.toFixed(2)}
                    soldLastWeek={10}
                  />
                ))}
            </tbody>
          </table>
        </div>
      ))}
    </div>
  );
};

export default AdminMenu;
