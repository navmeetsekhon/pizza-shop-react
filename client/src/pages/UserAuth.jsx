import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/UserAuth.css';

const UserAuth = () => {
  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title">User Authentication</h5>
              <div className="d-grid gap-2">
                <Link to="/login" className="btn btn-primary">Login</Link>
                <Link to="/signup" className="btn btn-secondary">Signup</Link>
                <Link to="/adminlogin" className="btn btn-success">Admin Login</Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UserAuth;
