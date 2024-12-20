import logo from './logo.svg';
import './App.css';
import Home from './Pages/Home';
import SimulationPage from './Pages/SimulationPage';

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';


function App() {
  return (
    <div className="App">
      <Router>
        <div>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/SimulationPage" element={<SimulationPage />} />
          </Routes>
        </div>
      </Router>
    </div>

  );
}

export default App;
