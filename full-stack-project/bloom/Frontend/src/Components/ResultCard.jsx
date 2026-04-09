import React from "react";
import BASE_URL from '../config.js'; 

const ResultCard = ({ results }) => {

  const mapwala = (long, lat) => {
    const url = `https://www.google.com/maps?q=${long},${lat}`;
    window.open(url, "_blank");
  };

  return (
    <div className="result-card">

      <div className="result-header">
        <div className="result-header-left">
          <h2 className="result-title">{results.shopName}</h2>
          <p className="result-address">{results.addressLine}</p>
        </div>

        <span className="result-city-badge">{results.city}</span>
      </div>

      <div className="result-body">

        <div className="result-box">
          <p className="result-label">State</p>
          <p className="result-value">{results.state}</p>
        </div>

        <div className="result-box">
          <p className="result-label">Country</p>
          <p className="result-value">{results.country}</p>
        </div>

        <div className="result-box">
          <p className="result-label">Contact</p>
          <p className="result-value result-contact">{results.contact}</p>
        </div>

        <div className="result-box">
          <p className="result-label">Latitude</p>
          <p className="result-value">{results.latitude}</p>
        </div>

        <div className="result-box">
          <p className="result-label">Longitude</p>
          <p className="result-value">{results.longitude}</p>
        </div>

        <div className="result-box">
          <p className="result-label">City</p>
          <p className="result-value">{results.city}</p>
        </div>

      </div>

      <div className="result-footer">

        <button
          className="result-btn result-btn-primary"
          onClick={() =>
            window.open(
              "mailto:shivrajak280@gmail.com?subject=Enquiry&body=Hello, I would like to know about...",
              "_blank"
            )
          }
        >
          Contact
        </button>

        <button
          className="result-btn result-btn-secondary"
          onClick={() => { mapwala(results.longitude, results.latitude) }}
        >
          View Map
        </button>

      </div>
    </div>
  );
};

export default ResultCard;
