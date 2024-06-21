import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../../styles/Sidebar.css";

function DashboardSidebar({ setPage }) {
  return (
    <div className="bg-light border-right" id="sidebar-wrapper">
      <div className="sidebar-heading">Admin Dashboard</div>
      <div className="list-group list-group-flush">
        <a
          href="#"
          className="list-group-item list-group-item-action bg-light"
          onClick={() => setPage("dashboard")}
        >
          Dashboard
        </a>
        <a
          href="#"
          className="list-group-item list-group-item-action bg-light"
          onClick={() => setPage("orders")}
        >
          Orders
        </a>
        <a
          href="#"
          className="list-group-item list-group-item-action bg-light"
          onClick={() => setPage("menu")}
        >
          Menu
        </a>
      </div>
    </div>
  );
}

export default DashboardSidebar;
