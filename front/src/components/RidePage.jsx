import React, { useState, useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { useVehicleTypes } from '../contexts/VehicleTypesContext';
import api from '../services/api';
import Navbar from './Navbar';

const RidePage = () => {
  const [searchParams] = useSearchParams();
  const vehicleId = searchParams.get('vehicleId');
  const [allVehicles, setAllVehicles] = useState([]);
  const [filteredVehicles, setFilteredVehicles] = useState([]);
  const [vehicleTypes, setVehicleTypes] = useState([]);
  const [selectedType, setSelectedType] = useState('');
  const [selectedVehicle, setSelectedVehicle] = useState(vehicleId || '');
  const [cities, setCities] = useState([]);
  const [selectedCity, setSelectedCity] = useState('');
  const [isRiding, setIsRiding] = useState(false);
  const [startTime, setStartTime] = useState(null);
  const [currentDuration, setCurrentDuration] = useState(0);
  const { user } = useAuth();
  const { vehicleTypeNames } = useVehicleTypes();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [vehicleResponse, cityResponse] = await Promise.all([
          api.get('vozilo'),
          api.get('grad')
        ]);
        setAllVehicles(vehicleResponse.data);
        setFilteredVehicles(vehicleResponse.data);
        const types = [...new Set(vehicleResponse.data.map(v => v.tipVozilaID))];
        setVehicleTypes(types);

        setCities(cityResponse.data);
      } catch (err) {
        console.error('Error fetching data:', err);
      }
    };
    fetchData();
  }, []);

  useEffect(() => {
    let interval;
    if (isRiding) {
      interval = setInterval(() => {
        setCurrentDuration(Math.floor((Date.now() - startTime) / 1000));
      }, 1000);
    }
    return () => clearInterval(interval);
  }, [isRiding, startTime]);

  const handleFilterVehicles = () => {
    if (selectedType === '') {
      setFilteredVehicles(allVehicles);
    } else {
      setFilteredVehicles(allVehicles.filter(v => v.tipVozilaID.toString() === selectedType));
    }
    setSelectedVehicle('');
  };

  const handleClearFilter = () => {
    setSelectedType('');
    setFilteredVehicles(allVehicles);
    setSelectedVehicle('');
  };

  const handleStart = () => {
    if (!selectedVehicle || !selectedCity) return;
    const confirmStart = window.confirm('Are you sure you want to start the ride?');
    if (confirmStart) {
      setIsRiding(true);
      setStartTime(Date.now());
    }
  };

  const handleStop = async () => {
    const endTime = Date.now();
    const durationSeconds = (endTime - startTime) / 1000;
    const vehicle = allVehicles.find(v => v.id.toString() === selectedVehicle);
    const price = (durationSeconds / 60) * vehicle.cenaPoMinutu;
    const minutes = Math.floor(durationSeconds / 60);
    const seconds = Math.floor(durationSeconds % 60);

    try {
      const vremeString = new Date(startTime).toISOString().slice(0, 19);
      await api.post('voznja', {
        voziloId: parseInt(selectedVehicle),
        gradID: parseInt(selectedCity),
        vreme: vremeString,
        trajanjeVoznje: Math.floor(durationSeconds),
        cena: price,
      });
      alert(`Ride completed!\nDuration: ${minutes} min ${seconds} sec\nCost: ${price.toFixed(2)} €`);
      setIsRiding(false);
      setStartTime(null);
      setCurrentDuration(0);
    } catch (err) {
      console.error('Error submitting ride:', err);
      alert('Error submitting ride');
    }
  };

  const formatDuration = (seconds) => {
    const min = Math.floor(seconds / 60);
    const sec = seconds % 60;
    return `${min}:${sec.toString().padStart(2, '0')}`;
  };

  const canStart = selectedVehicle && selectedCity && !isRiding;

  return (
    <div className="ride">
      <Navbar />
      <h1>Start Your Ride</h1>
      <div className="ride-section">
        <div className="filter-section">
          <h3>Filter Vehicles</h3>
          <select value={selectedType} onChange={(e) => setSelectedType(e.target.value)} className="type-select">
            <option value="">All Types</option>
            {vehicleTypes.map(type => (
              <option key={type} value={type}>{vehicleTypeNames[type] || `Type ${type}`}</option>
            ))}
          </select>
          <button onClick={handleFilterVehicles} className="find-btn">Find</button>
          <button onClick={handleClearFilter} className="clear-btn">X</button>
        </div>
        <div className="selection-section">
          <div className="form-group">
            <label>Select Vehicle:</label>
            <select value={selectedVehicle} onChange={(e) => setSelectedVehicle(e.target.value)} className="vehicle-select">
              <option value="">Choose a vehicle</option>
              {filteredVehicles.map(vehicle => (
                <option key={vehicle.id} value={vehicle.id}>
                {vehicleTypeNames[vehicle.tipVozilaID] || `Type ${vehicle.tipVozilaID}`} - Price {vehicle.cenaPoMinutu}/min
                </option>
              ))}
            </select>
          </div>
          <div className="form-group">
            <label>Select City:</label>
            <select value={selectedCity} onChange={(e) => setSelectedCity(e.target.value)} className="city-select">
              <option value="">Choose a city</option>
              {cities.map(city => (
                <option key={city.id} value={city.id}>{city.naziv}</option>
              ))}
            </select>
          </div>
        </div>
        <div className="ride-controls">
          {!isRiding ? (
            <button onClick={handleStart} disabled={!canStart} className="start-btn">Start Ride</button>
          ) : (
            <div>
              <p className="duration">Duration: {formatDuration(currentDuration)}</p>
              <button onClick={handleStop} className="stop-btn">Stop Ride</button>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default RidePage;