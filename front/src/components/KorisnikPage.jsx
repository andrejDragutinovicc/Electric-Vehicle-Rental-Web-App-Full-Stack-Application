import React, { useState, useEffect } from 'react';
import api from '../services/api';
import Navbar from './Navbar';

const KorisnikPage = () => {
  const [korisnici, setKorisnici] = useState([]);
  const [search, setSearch] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchKorisnici = async () => {
      setLoading(true);
      try {
        const response = await api.get(`korisnici/search?username=${encodeURIComponent(search)}`);
        setKorisnici(response.data);
        setError('');
      } catch (err) {
        setError('Greška prilikom učitavanja korisnika.');
        console.error('Error fetching korisnici:', err);
      } finally {
        setLoading(false);
      }
    };
    fetchKorisnici();
  }, [search]);

  return (
    <div className="korisnici">
      <Navbar />
      <h1>Korisnici</h1>
      <div className="filter-section">
        <input
          type="text"
          className="search-input"
          placeholder="Pretraži po korisničkom imenu..."
          value={search}
          onChange={e => setSearch(e.target.value)}
        />
      </div>
      {loading && <p className="loading-text">Učitavanje...</p>}
      {error && <p className="error">{error}</p>}
      {!loading && !error && korisnici.length === 0 && (
        <p className="loading-text">Nema korisnika koji odgovaraju pretrazi.</p>
      )}
      <div className="korisnici-list">
        {korisnici.map(k => (
          <div key={k.id} className="korisnik-card">
            <h2>{k.username}</h2>
            <p><strong>Email:</strong> {k.email}</p>
            <p>
              <span className={`role-badge ${k.role === 'ADMIN' ? 'role-admin' : 'role-user'}`}>
                {k.role}
              </span>
            </p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default KorisnikPage;
