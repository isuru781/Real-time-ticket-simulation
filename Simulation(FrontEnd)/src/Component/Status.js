import React, { useState } from 'react';
import axios from 'axios';
import './start.css';



export default function Status() {
  const [ticketStatus, setTicketStatus] = useState(null); // Stores the ticket data
  const [errorMessage, setErrorMessage] = useState(''); // Stores error messages
  const [isLoading, setIsLoading] = useState(false); // Tracks loading state

  const status = async () => {
    setErrorMessage('');
    setTicketStatus(null);
    setIsLoading(true);

    try {
      // Send GET request to the backend
      const response = await axios.get('http://localhost:8081/api/v1/simulation/status');
    // get data from server
      setTicketStatus(response.data.data);
    } catch (error) {
      // Handle errors
      if (error.response) {
        setErrorMessage(error.response.data.message || 'Failed to get ticket status.');
      } else {
        setErrorMessage('An error  communicating with the server.');
      }
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className='back' style={{ marginTop: '20px', textAlign: 'center'}}>
      <button onClick={status} disabled={isLoading} className='button'>
        {isLoading ? 'Loading...' : 'Ticket Status'}
      </button>
      {/* states of simulation button */}

      {errorMessage && <p style={{ color: 'red' ,marginTop: '20px', textAlign: 'center'}}>{errorMessage}</p>}
      {/* error message */}
      {ticketStatus && (
        <div>
          <h2>Current Ticket Status:</h2>
          <p>{ticketStatus}</p>
        </div>
        // print ticket status
      )}
    </div>
  );
}
