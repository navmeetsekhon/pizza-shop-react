import React, { useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const AdminLogin = ({ handleAdminLogin }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/auth/adminLogin', { username, password });
      if (response.status === 200) {
        localStorage.setItem("user",{
          userId:1,
          name: username,
          email:password
        })
        handleAdminLogin(); // Call parent function to handle successful admin login
      }
    } catch (error) {
      setError('Invalid credentials');
    }
  };

  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <form onSubmit={handleSubmit}>
            <h3>Admin Login</h3>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">Username</label>
              <input type="text" className="form-control" id="username" value={username} onChange={(e) => setUsername(e.target.value)} />
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">Password</label>
              <input type="password" className="form-control" id="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            </div>
            <button type="submit" className="btn btn-primary">Login</button>
            <Link to="/signup" className="btn btn-link">Don't have an account? Signup</Link>
            {error && <p className="text-danger">{error}</p>}
          </form>
        </div>
      </div>
    </div>
  );
}

export default AdminLogin;
