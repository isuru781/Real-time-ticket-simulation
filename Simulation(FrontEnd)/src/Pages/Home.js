import React from 'react';
import './Home.css';

import {Link} from 'react-router-dom';

export default function Home() {
  return (
    <div className="welcome-page">
      <div className='box'>
      <h1>Welcome to the Ticket Simulation System</h1>
      <Link to="/SimulationPage"> <button className="start-button"  >
      Start Simulation &#x2192;
      </button></Link> 
      </div>
    </div>
  );
}