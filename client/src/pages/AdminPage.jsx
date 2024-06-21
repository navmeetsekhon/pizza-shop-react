import React, { useEffect,useState } from "react";
import "../styles/AdminPage.css";
import Dashboard from "../components/Admin/Dashboard";
import Orders from "../components/Admin/Orders";
import AdminMenu from "../components/Admin/AdminMenu";
import DashboardSidebar from "../components/Admin/DashboardSidebar";
import Api from "../helper/Api.json";
import axios from "axios";
const ApiPrefix = Api.prefix;
const user = {
  userId:1,
  name: "john doe",
  email: "johndoe@example.com"
};

const getCompletedOrders = (data) => {
  return data.filter(order => order.orderStatus === true);
};
const calculateTotalSale = (data) => {
  return data.reduce((total, order) => {
    if (order.orderStatus) {
      return total + order.orderTotalAmount;
    }
    return total;
  }, 0);
};
const getCancelledOrders = (data) => {
  return data.filter(order => order.orderStatus === false);
};
function AdminPage({ handleLogout }) {


  const [pendingOrders,setPendingOrders] = useState([]);
  const [allOrders,setAllOrders] = useState([]);
  const [totalSale,setTotalSale] = useState(0);
  const [completedOrders,setCompletedOrdes] = useState([]);
  const [cancelledOrders,setCancelledOrders] = useState([]);
  const [page, setPage] = useState("dashboard");

  const [menu,setMenu] = useState([]);


useEffect(() => {
  const fetchAllOrders = async (userId) => {
    try {
      const response = await fetch(ApiPrefix + "/order/getAllUserOrders", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: userId,
        }),
      });
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();
      if (data.code !== 200) {
        throw new Error(
          `Server error! status: ${data.code} , ${data.message} , ${data.errorCode}`
        );
      }
      const sortedAllOrders = data.data.sort((a, b) => new Date(b.orderDate) - new Date(a.orderDate));
      
      const sortedPendingOrders = sortedAllOrders.filter(order => !order.orderStatus)
                                                .sort((a, b) => new Date(b.orderDate) - new Date(a.orderDate));
      
      
      const totalSaleAmount = calculateTotalSale(data.data);  
      const cancelledOrders = getCancelledOrders(data.data);
      const completedOrders = getCompletedOrders(data.data);
      setAllOrders(sortedAllOrders);
      setPendingOrders(sortedPendingOrders);
      setTotalSale(totalSaleAmount);
      setCancelledOrders(cancelledOrders);                  
      setCompletedOrdes(completedOrders);                   
    } catch (e) {
      console.error(e);
    }
  };
  fetchAllOrders(user.userId);
  const fetchMenu = async ()=>{
    try{
      const response = await axios.get(ApiPrefix+"/menu");
      setMenu(response.data.data);
    }
    catch(error){
      console.error(error);
    }
  }
  fetchMenu();
}, []);

const markAsCompleted = async (curOrderId) => {
  try {
    const response = await fetch(ApiPrefix + "/order/markAsCompleted", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id: curOrderId,
      }),
    });
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    const data = await response.json();
    if (data.code !== 200) {
      throw new Error(
        `Server error! status: ${data.code} , ${data.message} , ${data.errorCode}`
      );
    } else {
      const updatedOrders = allOrders.map((order) => {
        if (order.orderId === curOrderId) {
          return { ...order, orderStatus: true }; // Mark as completed
        }
        return order;
      });
      setAllOrders(updatedOrders);
      setPendingOrders(pendingOrders.filter((order) => order.orderId !== curOrderId));
      console.log("order marked completed.");
    }
  } catch (error) {}
};
const renderPage = () => {
  switch (page) {
    case "dashboard":
      return <Dashboard allOrders = {allOrders} pendingOrders = {pendingOrders} totalSale = {totalSale} cancelledOrders = {cancelledOrders}/>;
    case "orders":
      // return <Orders/>
      return <Orders allOrders = {allOrders} pendingOrders = {pendingOrders} cancelledOrders = {cancelledOrders} completedOrders = {completedOrders} markAsCompleted = {markAsCompleted}/>;
    case "menu":
      return <AdminMenu menu = {menu} />;
    default:
      return <Dashboard />;
  }
};

  return (
    <div className="d-flex" id="wrapper">
      <DashboardSidebar setPage={setPage} />
      <div id="page-content-wrapper" className="p-4">
        {renderPage()}
        <div className="logout-button-container">
          <button className="btn btn-danger mt-3" onClick={handleLogout}>
            Log Out
          </button>
        </div>
      </div>
    </div>
  );
}

export default AdminPage;
