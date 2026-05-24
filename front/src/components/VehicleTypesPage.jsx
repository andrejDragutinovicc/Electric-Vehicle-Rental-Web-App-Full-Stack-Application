import React, { useState, useEffect } from 'react';
import api from '../services/api';
import Navbar from './Navbar';

const VehicleTypesPage = () => {
  const [vehicleTypes, setVehicleTypes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchVehicleTypes = async () => {
      try {
        const response = await api.get('tipvozila');
        setVehicleTypes(response.data);
      } catch (err) {
        setError('Greška prilikom učitavanja tipova vozila.');
        console.error('Error fetching vehicle types:', err);
      } finally {
        setLoading(false);
      }
    };
    fetchVehicleTypes();
  }, []);

  return (
    <div className="vehicle-types">
      <Navbar />
      <h1>Tipovi Vozila</h1>
      {loading && <p className="loading-text">Učitavanje...</p>}
      {error && <p className="error">{error}</p>}
      <div className="vehicle-types-list">
        {vehicleTypes.map(type => (
          <div key={type.id} className="vehicle-type-card">
            <h2>{type.ime}</h2>
            {type.opis && <p>{type.opis}</p>}
          </div>
        ))}
      </div>
    </div>
  );
};

export default VehicleTypesPage;
