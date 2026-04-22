import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import api from '../services/api';
import { useVehicleTypes } from '../contexts/VehicleTypesContext';
import Navbar from './Navbar';

const VehiclesPage = () => {
  const [allVehicles, setAllVehicles] = useState([]);
  const [filteredVehicles, setFilteredVehicles] = useState([]);
  const [vehicleTypes, setVehicleTypes] = useState([]);
  const [selectedType, setSelectedType] = useState('');
  const { vehicleTypeNames } = useVehicleTypes();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const vehicleResponse = await api.get('vozilo');
        setAllVehicles(vehicleResponse.data);
        setFilteredVehicles(vehicleResponse.data);
        const types = [...new Set(vehicleResponse.data.map(v => v.tipVozilaID))];
        setVehicleTypes(types);
      } catch (err) {
        console.error('Error fetching vehicles:', err);
      }
    };
    fetchData();
  }, []);

  const handleFilter = () => {
    if (selectedType === '') {
      setFilteredVehicles(allVehicles);
    } else {
      setFilteredVehicles(allVehicles.filter(v => v.tipVozilaID.toString() === selectedType));
    }
  };

  const handleClear = () => {
    setSelectedType('');
    setFilteredVehicles(allVehicles);
  };

  return (
    <div className="vehicles">
      <Navbar />
      <h1>Vehicles</h1>
      <div className="filter-section">
        <select value={selectedType} onChange={(e) => setSelectedType(e.target.value)} className="type-select">
          <option value="">All Types</option>
          {vehicleTypes.map(type => (
            <option key={type} value={type}>{vehicleTypeNames[type] || `Type ${type}`}</option>
          ))}
        </select>
        <button onClick={handleFilter} className="find-btn">Find</button>
        <button onClick={handleClear} className="clear-btn">X</button>
      </div>
      <div className="vehicle-list">
        {filteredVehicles.map(vehicle => (
          <div key={vehicle.id} className="vehicle-card">
            <p><strong>Type:</strong> {vehicleTypeNames[vehicle.tipVozilaID] || `Type ${vehicle.tipVozilaID}`}</p>
            <p><strong>Battery:</strong> {vehicle.baterija}%</p>
            <p><strong>Mileage:</strong> {vehicle.kilometraza}</p>
            <p><strong>Price/min:</strong> {vehicle.cenaPoMinutu}</p>
            <Link to={`/ride?vehicleId=${vehicle.id}`} className="select-btn">Select for Ride</Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default VehiclesPage;