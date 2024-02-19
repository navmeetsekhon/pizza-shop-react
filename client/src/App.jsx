import React from "react";
import Navbar from "./components/Navbar";
import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Menu from "./pages/Menu";
import About from "./pages/About";
import Contact from "./pages/Contact";
import CartPage from "./pages/CartPage";
import Footer from "./components/Footer";
import Profile from "./pages/Profile";
import AdminPage from './pages/AdminPage';
function App() {
  const user = {
    userId:1,
    name:"John Doe",
    email:"johndoe@example.com",
  }
  return (
    <>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/Menu" element={<Menu user = {user}/>} />
          <Route path="/About" element={<About />} />
          <Route path="/Contact" element={<Contact />} />
          <Route path="/Cartpage" element={<CartPage user = {user}/>} />
          <Route path="/Profile" element={<Profile user = {user}/>} />
          <Route path="/Adminpage" element={<AdminPage />}/>
        </Routes>
      </Router>
      <Footer />
    </>
  );
}

export default App;
