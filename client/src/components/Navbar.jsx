import React from "react";
import { Link, useMatch, useResolvedPath } from "react-router-dom";
import "../styles/Navbar.css";
function Navbar2() {
  return (
      <nav className="nav">
        <Link to="/" className="site-title">
          Pizza by Alfredo
        </Link>
        <ul>
          <CustomLink to="/">Home</CustomLink>
          <CustomLink to="/Menu">Menu</CustomLink>
          <CustomLink to="/About">About</CustomLink>
          <CustomLink to="/contact">Contact</CustomLink>
          <CustomLink to="/cartpage">Cart</CustomLink>
          <CustomLink to="/profile">Profile</CustomLink>
          <CustomLink to="/Adminpage">Admin</CustomLink>
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
