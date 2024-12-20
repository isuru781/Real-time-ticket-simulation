import React, { useState } from 'react';
import axios from 'axios';
import './start.css';
function Start() {
    const [formData, setFormData] = useState({
        totalTickets: '',
        ticketReleaseRate: '',
        customerRetrievalRate: '',
        maxTicketCapacity: '',
    }); // Stores the form data
    const [responseMessage, setResponseMessage] = useState('');// Stores the response message
    const [errorMessage, setErrorMessage] = useState('');// Stores error messages

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };// Handle form input changes

    const Submit = async (e) => {//  form submission
        e.preventDefault();
        setResponseMessage('');
        setErrorMessage('');

        try {
            const response = await axios.post('http://localhost:8081/api/v1/simulation/start', null, {
                params: {
                    totalTickets: formData.totalTickets,
                    ticketReleaseRate: formData.ticketReleaseRate,
                    customerRetrievalRate: formData.customerRetrievalRate,
                    MaxTicketCapacity: formData.maxTicketCapacity,
                },
            });// Make the POST request
            setResponseMessage(response.data.message);// Set the response message
        } catch (error) {
            if (error.response) {
                setErrorMessage(error.response.data.message);//get the error message
            } else {
                setErrorMessage('An error to communicating with the server.');//error message
            }
        }
    };

    
    return (
        <div style={{ marginTop: '20px', textAlign: 'center'}} >
            <h1 className='sim'>Start Simulation</h1>
            <form className='lab' onSubmit={Submit}>
                <div className='form'>
                    <label className='label'>Total Tickets:</label>
                    <input className='input'
                        type="number"
                        name="totalTickets"
                        value={formData.totalTickets}
                        onChange={handleChange}
                        min="50"
                        max="500"
                        required
                    />
                </div>
                <div className='form'>
                    <label className='label'>Ticket Release Rate:</label>
                    <input className='input'
                        type="number"
                        name="ticketReleaseRate"
                        value={formData.ticketReleaseRate}
                        onChange={handleChange}
                        min="1"
                        max="5"
                        required
                    />
                </div>
                <div className='form'>
                    <label className='label'>Customer Retrieval Rate:</label>
                    <input className='input'
                        type="number"
                        name="customerRetrievalRate"
                        value={formData.customerRetrievalRate}
                        onChange={handleChange}
                        min="1"
                        max="5"
                        required
                    />
                </div>
                <div className='form'>
                    <label className='label'>Max Ticket Capacity:</label>
                    <input  className='input'
                        type="number"
                        name="maxTicketCapacity"
                        value={formData.maxTicketCapacity}
                        onChange={handleChange}
                        min="5"
                        max="50"
                        required
                    />
                </div>
                <button type="submit" className='button'>Start Simulation</button>
            </form>
            {responseMessage && <p>{responseMessage}</p>}
            {errorMessage && <p style={{ color: 'red', fontWeight:"" }}>{errorMessage}</p>}
        </div>
    );
}

export default Start;
