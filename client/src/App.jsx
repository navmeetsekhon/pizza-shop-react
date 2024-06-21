import React, { useState, useEffect } from "react";
import Navbar from "./components/Navbar";
import "./App.css";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import Home from "./pages/Home";
import Menu from "./pages/Menu";
import About from "./pages/About";
import Contact from "./pages/Contact";
import CartPage from "./pages/CartPage";
import Footer from "./components/Footer";
import Profile from "./pages/Profile";
import AdminPage from './pages/AdminPage';
// import LoginForm from './components/Login';
// import SignupForm from './components/Signup';
import AdminLoginForm from './components/AdminLogin';

function App() {
  // const [loggedIn, setLoggedIn] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const user = {
    userId:1,
    name: "john doe",
    email: "johndoe@example.com"
  };
  useEffect(() => {
    
    const userIsAdmin = localStorage.getItem("isAdmin") === "true";
    setIsAdmin(userIsAdmin);
  }, []);


  const handleLogout = () => {
   
    setIsAdmin(false); 
    localStorage.removeItem("isAdmin");
  };
  const [cartCount, setCartCount] = useState(0);

  // Function to handle admin login
  const handleAdminLogin = () => {
    setIsAdmin(true);
    localStorage.setItem("isAdmin", "true");
    Navigate('/adminpage');
  };
  // Cart page function
  const incrementCartCount = ()=>{
    setCartCount(cartCount+1);
  }
  const setCartCountCustom = (count) => {
    
    setCartCount(count);
    // console.log(cartCount);
  };

  return (
    <>
      <Router>
        <Navbar cartCount={cartCount} isAdmin={isAdmin} handleLogout={handleLogout} />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/Menu" element={<Menu user = {user} incrementCartCount={incrementCartCount} />} />
          <Route path="/About" element={<About />} />
          <Route path="/Contact" element={<Contact />} />
          <Route path="/Cartpage" element={<CartPage user = {user} setCartCountCustom = {setCartCountCustom}/> } />
          <Route path="/Profile" element={<Profile user = {user}/>} />
          {isAdmin ? (
            <Route path="/Adminpage" element={<AdminPage handleLogout={handleLogout} />} />
          ) : (
            <Route path="/adminlogin" element={<AdminLoginForm handleAdminLogin={handleAdminLogin} />} />
          )}
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </Router>
      <Footer />
    </>
  );
}

export default App;
