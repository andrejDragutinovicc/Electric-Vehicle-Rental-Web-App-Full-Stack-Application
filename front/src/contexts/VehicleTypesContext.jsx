import React, { createContext, useContext, useState, useEffect } from 'react';
import api from '../services/api';

const VehicleTypesContext = createContext();

export const useVehicleTypes = () => useContext(VehicleTypesContext);

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

export const VehicleTypesProvider = ({ children }) => {
  const [vehicleTypeNames, setVehicleTypeNames] = useState(fallbackTypeNames);

  useEffect(() => {
    const fetchTypes = async () => {
      try {
        const response = await api.get('tipvozila');
        const typeMap = { ...fallbackTypeNames };
        if (response.data && response.data.length > 0) {
          response.data.forEach(type => {
            typeMap[type.id] = type.naziv;
          });
        }
        setVehicleTypeNames(typeMap);
      } catch (err) {
        console.error('Error fetching vehicle types:', err);
        setVehicleTypeNames(fallbackTypeNames);
      }
    };
    fetchTypes();
  }, []);

  return (
    <VehicleTypesContext.Provider value={{ vehicleTypeNames }}>
      {children}
    </VehicleTypesContext.Provider>
  );
};