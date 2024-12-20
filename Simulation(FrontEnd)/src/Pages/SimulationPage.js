import React from 'react'
import Status from '../Component/Status';
import Start from '../Component/Start';
import Stop from '../Component/Stop';
import Reset from '../Component/Reset';
import RealTimeConsoleLogger from '../Component/RealTimeConsoleLogger';
import {Link} from 'react-router-dom';



export default function SimulationPage() {
  return (
    <div className='Simulation'>
    <Start/>
    <Stop/>
    <Status/>
    <RealTimeConsoleLogger/>
    <Reset/>

        <Link to="/"> <button className="start-button"  >
            &#x2190; Back to Home
        </button></Link> </div>
  )
}
