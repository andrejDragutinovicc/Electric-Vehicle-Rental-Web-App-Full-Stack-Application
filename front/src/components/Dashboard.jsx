import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import Navbar from './Navbar';

const Dashboard = () => {
  const { user, logout } = useAuth();

  return (
    <div className="dashboard">
      <Navbar />
      <div className="hero-section">
        <h1>Rent a Bike</h1>
        <p>Your ultimate vehicle rental experience</p>
      </div>
      <div className="dashboard-content">
        <h2>Quick Actions</h2>
        <div className="actions">
          <Link to="/vehicles" className="action-btn">Browse Vehicles</Link>
          <Link to="/ride" className="action-btn">Start a Ride</Link>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;