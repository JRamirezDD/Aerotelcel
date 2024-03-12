import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './ATBrowser.css';
import logoImage from './images-AT/logo.png';


const AeroTelcelApp = () => {
    const navigate = useNavigate();
    const [IATA, setAirportId] = useState('');
    const [flightID, setFlightId] = useState('');

    return (
        <div className="a-tbrowser">
            <div className="aero-telcel">AeroTelcel</div>

            {/* Rectangle 1 (Search by flightID) */}
            <div className="rectangle-1"></div>
            <div className="rectangle-2">
                <input className="input-text" type="text" placeholder="Search Flight by ID"
                       value={flightID}
                       onChange={(event) => setFlightId(event.target.value)}
                       maxLength={8}
                       minLength={3}/>
            </div>
            <div className="rectangle rectangle-3-container">
                <div className="button" onClick={()=>{navigate('/ATFlightPage', {replace: true, state: {flightID}})}}>Browse</div>
            </div>
            <div className="search-by-flight">Search by flight:</div>

            {/* Rectangle 2 (Search by route) */}
            <div className="rectangle-4"></div>
            <div className="origin">Origin:</div>
            <div className="rectangle-5">
                <input className="input-text" type="text" placeholder="Origin City" />
            </div>
            <div className="rectangle-6">
                <input className="input-text" type="text" placeholder="Destiny City" />
            </div>
            <div className="rectangle rectangle-6-container">
                <a href="/ATFlightList">
                    <div className="buttonW">Browse</div>
                </a>
            </div>
            <div className="destiny">Destiny:</div>


            <div className="search-by-route">Search by route:</div>

            {/* Rectangle 3(Search by AirportID) */}
            <div className="rectangle-8"></div>
            <div className="rectangle-10">
                <input
                    className="input-text"
                    type="text"
                    placeholder="Search Airport by ID"
                    value={IATA}
                    onChange={(event) => setAirportId(event.target.value)}
                    maxLength={3}
                    minLength={3}
                />
            </div>
            <div className="rectangle rectangle-11-container">
                    <div className="button" onClick={()=>{navigate('/ATAirportPage', {replace: true, state: {IATA}})}}>Browse</div>
            </div>
            <div className="search-airport">Search airport:</div>

            {/* Image-1 as a button */}
            <div className="image-1-container">
                <Link to="/ATBrowser">
                    <div className="buttonW">
                        <img src={logoImage} alt="logo" />
                    </div>
                </Link>
            </div>
        </div>
    );
};

export default AeroTelcelApp;
