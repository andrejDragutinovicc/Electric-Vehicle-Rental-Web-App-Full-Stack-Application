import React, { useState, useEffect } from 'react';
import api from '../services/api';
import Navbar from './Navbar';

const GradoviPage = () => {
  const [gradovi, setGradovi] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchGradovi = async () => {
      try {
        const response = await api.get('grad');
        setGradovi(response.data);
      } catch (err) {
        setError('Greška prilikom učitavanja gradova.');
        console.error('Error fetching gradovi:', err);
      } finally {
        setLoading(false);
      }
    };
    fetchGradovi();
  }, []);

  return (
    <div className="gradovi">
      <Navbar />
      <h1>Gradovi</h1>
      {loading && <p className="loading-text">Učitavanje...</p>}
      {error && <p className="error">{error}</p>}
      <div className="gradovi-list">
        {gradovi.map(grad => (
          <div key={grad.id} className="grad-card">
            <h2>{grad.naziv}</h2>
            {grad.postanskiBRoj && <p><strong>Poštanski broj:</strong> {grad.postanskiBRoj}</p>}
          </div>
        ))}
      </div>
    </div>
  );
};

export default GradoviPage;
