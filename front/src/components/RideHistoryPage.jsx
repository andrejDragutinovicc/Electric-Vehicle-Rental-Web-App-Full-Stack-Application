import React, { useState, useEffect } from 'react';
import { useAuth } from '../contexts/AuthContext';
import { useVehicleTypes } from '../contexts/VehicleTypesContext';
import api from '../services/api';
import Navbar from './Navbar';

const RideHistoryPage = () => {
  const [rideHistory, setRideHistory] = useState([]);
  const [vehicles, setVehicles] = useState([]);
  const [cities, setCities] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const { user } = useAuth();
  const { vehicleTypeNames } = useVehicleTypes();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [historyResponse, vehiclesResponse, citiesResponse] = await Promise.all([
          api.get('voznja/history'),
          api.get('vozilo'),
          api.get('grad')
        ]);
        setRideHistory(historyResponse.data);
        setVehicles(vehiclesResponse.data);
        setCities(citiesResponse.data);
      } catch (err) {
        console.error('Error fetching data:', err);
        setError('Failed to load ride history');
      } finally {
        setLoading(false);
      }
    };

    if (user) {
      fetchData();
    }
  }, [user]);

  const getVehicleName = (vehicleId) => {
    const vehicle = vehicles.find(v => v.id === vehicleId);
    const typeName = vehicle ? vehicleTypeNames[vehicle.tipVozilaID] || `Type ${vehicle.tipVozilaID}` : `Type ?`;
    const price = vehicle ? `${vehicle.cenaPoMinutu}€/min` : '';
    return `${typeName}${price ? ` - ${price}` : ''}`;
  };

  const getCityName = (cityId) => {
    const city = cities.find(c => c.id === cityId);
    return city ? city.naziv : `City ${cityId}`;
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleString('sr-RS');
  };

  const formatDuration = (seconds) => {
    const min = Math.floor(seconds / 60);
    const sec = seconds % 60;
    return `${min}:${sec.toString().padStart(2, '0')}`;
  };

  if (loading) {
    return (
      <div className="ride-history">
        <Navbar />
        <h1>Ride History</h1>
        <p>Loading...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="ride-history">
        <Navbar />
        <h1>Ride History</h1>
        <p className="error">{error}</p>
      </div>
    );
  }

  return (
    <div className="ride-history">
      <Navbar />
      <h1>Ride History</h1>
      {rideHistory.length === 0 ? (
        <p>No rides found.</p>
      ) : (
        <div className="history-table">
          <table>
            <thead>
              <tr>
                <th>Date & Time</th>
                <th>Vehicle</th>
                <th>City</th>
                <th>Duration</th>
                <th>Cost</th>
              </tr>
            </thead>
            <tbody>
              {rideHistory.map((ride, index) => (
                <tr key={index}>
                  <td>{formatDate(ride.vreme)}</td>
                  <td>{getVehicleName(ride.voziloId)}</td>
                  <td>{getCityName(ride.gradID)}</td>
                  <td>{formatDuration(ride.trajanjeVoznje)}</td>
                  <td>{ride.cena.toFixed(2)} €</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default RideHistoryPage;