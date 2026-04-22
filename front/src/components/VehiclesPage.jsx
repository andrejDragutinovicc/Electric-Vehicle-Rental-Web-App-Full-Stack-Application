import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import api from '../services/api';
import Navbar from './Navbar';

const VehiclesPage = () => {
  const [allVehicles, setAllVehicles] = useState([]);
  const [filteredVehicles, setFilteredVehicles] = useState([]);
  const [vehicleTypes, setVehicleTypes] = useState([]);
  const [vehicleTypeNames, setVehicleTypeNames] = useState({});
  const [selectedType, setSelectedType] = useState('');

  // Fallback type names if API fails
  const fallbackTypeNames = {
    1: 'Electric Bike',
    2: 'Scooter',
    3: 'Electric Scooter',
    4: 'Bicycle',
    5: 'Motorcycle',
    6: 'Car',
    7: 'Electric Car',
    8: 'Truck',
    9: 'Van',
    10: 'Bus',
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [vehicleResponse, typeResponse] = await Promise.all([
          api.get('vozilo'),
          api.get('tipvozila').catch(() => ({ data: [] })) // Fallback if endpoint doesn't exist
        ]);
        setAllVehicles(vehicleResponse.data);
        setFilteredVehicles(vehicleResponse.data);
        const types = [...new Set(vehicleResponse.data.map(v => v.tipVozilaID))];
        setVehicleTypes(types);
        
        // Create map of type ID to name, fallback to hardcoded if empty
        const typeMap = { ...fallbackTypeNames };
        if (typeResponse.data && typeResponse.data.length > 0) {
          typeResponse.data.forEach(type => {
            typeMap[type.id] = type.naziv;
          });
        }
        setVehicleTypeNames(typeMap);
      } catch (err) {
        console.error('Error fetching vehicles:', err);
        // Set fallback types
        setVehicleTypeNames(fallbackTypeNames);
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