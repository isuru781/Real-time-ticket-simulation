import React, { useState } from 'react';
import axios from 'axios';
import './start.css';
export default function Stop() {
    const [responseMessage, setResponseMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [isLoading, setIsLoading] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setResponseMessage('');
        setErrorMessage('');
        setIsLoading(true); // Start loading 

        try {
            // Make the POST request 
            const response = await axios.post('http://localhost:8081/api/v1/simulation/stop');
            
            // Set the response message
            setResponseMessage(response.data.data);  
        } catch (error) {
            // Error handling
            if (error.response) {
                // If the error has a response object, get the error message
                setErrorMessage(error.response.data.message || 'An error while stopping the simulation.');
            } else {
                setErrorMessage('An error  while communicating with the server.');
            }
        } finally {
            setIsLoading(false); // Stop loading spinner
        }
    };

    return (
        <div className='back' style={{ marginTop: '20px', textAlign: 'center'}}>
            <button onClick={handleSubmit} disabled={isLoading} className='button'>
                {isLoading ? 'Stopping Simulation...' : 'Stop Simulation'}
            </button>
            {/* stoping simulation button */}

            {responseMessage && (
                <div style={{ marginTop: '20px' ,textAlign: 'center' }}>
                    <h2>Simulation Status:</h2>
                    <p>{responseMessage}</p>
                </div>
                // print response of states of simulation
)}            
            {errorMessage && (
                <div style={{ marginTop: '20px', color: 'red',textAlign: 'center' }}>
                    <p>{errorMessage}</p>
                </div>
                // print error message it had error
            )}
        </div>
    );
}
