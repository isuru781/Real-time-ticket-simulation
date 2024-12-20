import React, { useEffect } from "react";
import axios from "axios";

export default function RealTimeConsoleLogger() {
  const fetchRealTimeData = async () => {
    try {
      const responses = await axios.get("http://localhost:8081/api/v1/simulation/realtime"); // Backend endpoint
    
        console.log("Real-Time Data:", responses.data.data); // Log the valid JSON response
    } catch (err) {
      console.error("Error fetching real-time data:", err.message); // Log errors
    }
  };

  useEffect(() => {
    fetchRealTimeData();
    const interval = setInterval(fetchRealTimeData, 3000); // get 3 sec interval
    return () => clearInterval(interval); // Cleanup component
  }, []);

  return null; // No frontend display
}
