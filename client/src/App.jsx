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
  return (
    <>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/Menu" element={<Menu />} />
          <Route path="/About" element={<About />} />
          <Route path="/Contact" element={<Contact />} />
          <Route path="/Cartpage" element={<CartPage />} />
          <Route path="/Profile" element={<Profile />} />
          <Route path="/Adminpage" element={<AdminPage />}/>
        </Routes>
      </Router>
      <Footer />
    </>
  );
}

export default App;
