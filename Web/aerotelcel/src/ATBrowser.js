import React from 'react';
import './ATBrowser.css'; // Import your styles here
import logoImage from './images-AT/logo.png'; // Correct the path to the image



const AeroTelcelApp = () => {
  return (
    <div className="a-tbrowser">
      <div className="aero-telcel">AeroTelcel</div>

      {/* Rectangle 1 (Search by flightID) */}
      <div className="rectangle-1"></div>
      <div className="rectangle-2">
        <input className="input-text" type="text" placeholder="Search Flight by ID" />
      </div>
      <div className="rectangle rectangle-3-container">
        <a href="/ATFlightPage">
          <div className="button">Browse</div>
        </a>
      </div>
      <div className="search-by-flight">Search by flight:</div>

      {/* Rectangle 2 (Search by route) */}
      <div className="rectangle-4"></div>
      <div className="origin">Origin:</div>
      <div className="rectangle-5">
        <input className="input-text" type="text" placeholder="Origin City" />
      </div>
      <div className="destiny">Destiny:</div>
      <div className="rectangle-6">
        <input className="input-text" type="text" placeholder="Destiny City" />
      </div>
      <div className="rectangle rectangle-6-container">
        <a href="/ATFlightList">
          <div className="buttonW">Browse</div>
        </a>
      </div>
      <div className="search-by-route">Search by route:</div>

      {/* Rectangle 3(Search by AirportID) */}
      <div className="rectangle-8"></div>
      <div className="rectangle-10">
        <input className="input-text" type="text" placeholder="Search Airport by ID" />
      </div>
      <div className="rectangle rectangle-11-container">
        <a href="/AtAirportPage">
          <div className="button">Browse</div>
        </a>
      </div>
      <div className="search-airport">Search airport:</div>

      {/* Image-1 as a button */}
      <div className="image-1-container">
        <a href="/ATBrowser">
          <div className="buttonW">
            <img src= {logoImage} alt="logo" />
          </div>
        </a>
      </div>
    </div>
  );
};

export default AeroTelcelApp;
