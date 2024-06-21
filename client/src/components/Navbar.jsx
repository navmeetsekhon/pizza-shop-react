import React from "react";
import { Link, useMatch, useResolvedPath } from "react-router-dom";
import { FaCartShopping } from "react-icons/fa6";
import "../styles/Navbar.css";

function Navbar2({ loggedIn, isAdmin, cartCount }) {
  return (
    <nav className="nav">
      <Link to="/" className="site-title">
        Pizza by Alfredo
      </Link>
      <ul>
        <CustomLink to="/">Home</CustomLink>
        <CustomLink to="/Menu">Menu</CustomLink>

        <CustomLink to="/profile">Profile</CustomLink>
        <CustomLink to="/About">About</CustomLink>
        <CustomLink to="/contact">Contact</CustomLink>
        {isAdmin ? (
          <CustomLink to="/Adminpage">Admin</CustomLink>
        ) : (
          <CustomLink to="/adminlogin">Admin Login</CustomLink>
        )}
        <CustomLink to="/cartpage">
          <FaCartShopping size={25} />{" "}
          <span className="cart-counters">{cartCount}</span>
        </CustomLink>
      </ul>
    </nav>
  );
}

function CustomLink({ to, children, ...props }) {
  const resolvedPath = useResolvedPath(to);
  const isActive = useMatch({ path: resolvedPath.pathname, end: true });
  return (
    <li>
      <Link className={isActive ? "active" : ""} to={to} {...props}>
        {" "}
        {children}{" "}
      </Link>
    </li>
  );
}

export default Navbar2;
