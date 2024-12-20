import React, { useState } from 'react';
import axios from 'axios';
import './start.css';
export default function Reset() {
  const [message, setMessage] = useState('');// Stores the message
  const [loading, setLoading] = useState(false);// Tracks loading state

  const handleReset = async () => {// Reset the simulation
    setLoading(true);
    try {
      const response = await axios.post('http://localhost:8081/api/v1/simulation/reset');// Make the POST request
      if (response.status === 200) {
        setMessage(response.data.message);
      } else {
        setMessage('Unexpected response from server.');
      }
    } catch (error) {// Error handling
      setMessage(`Error resetting simulation: ${error.response?.data?.message || error.message}`);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ textAlign: 'center', marginTop: '20px' }} className='back'>
      <button 
        onClick={handleReset} 
        disabled={loading} 
        className='button'
      >
        {loading ? 'Resetting...' : 'Reset Simulation'}
      </button>
      {/* reset button */}
      {message && (
        <div style={{ marginTop: '20px', color: message.includes('Error') ? 'red' : 'black' }}>
          {message}
        </div>
        // display massege
      )}
    </div>
  );
}
