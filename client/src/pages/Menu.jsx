import React, { useEffect, useState } from "react";
import MenuItem from "../components/MenuItem";
import "../styles/Menu.css";
function Menu() {

  const [menuItems, setMenuItems] = useState([]);

  useEffect(() => {
    const fetchMenu = async () => {
      try {
        const response = await fetch("http://localhost:8080/menu");
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const menuData = await response.json();
        setMenuItems(menuData.data);
      } catch (error) {
        console.error("Error fetching menu from server" + error.message);
      }
    };
    fetchMenu();
  }, []);


  return (
    <div className="menu">
      <h1 className="menuTitle">Our Menu</h1>
      <div className="menuList">
        {menuItems.map((item, key) => {
          return (
            <MenuItem
              key={key}
              userId={1} 
              itemId={item.itemId}
              itemName={item.itemName}
              itemPrice={item.itemPrice}
              itemCategory={item.itemCategory}
            />
          );
        })}
      </div>
    </div>
  );
}

export default Menu;
