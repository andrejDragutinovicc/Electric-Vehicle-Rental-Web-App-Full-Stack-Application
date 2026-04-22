import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

const Navbar = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/auth');
  };

  const userLabel = user && typeof user === 'object' ? user.username || user.email || user.id : user;

  return (
    <nav className="navbar">
      <div className="navbar-brand">
        <Link to="/dashboard">Rent a Bike</Link>
      </div>
      <div className="navbar-menu">
        <Link to="/dashboard">Dashboard</Link>
        <Link to="/vehicles">Vehicles</Link>
        <Link to="/ride">Ride</Link>
        <Link to="/history">History</Link>
      </div>
      <div className="navbar-user">
        <span>{userLabel}</span>
        <button onClick={handleLogout}>Logout</button>
      </div>
    </nav>
  );
};

export default Navbar;