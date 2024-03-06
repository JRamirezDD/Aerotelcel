import React from 'react';
import './AtFlightPage.css'; // Import your styles
import logoImage from  './images-AT/logo.png';
import heart from './images-AT/fullhart.png';
import star from './images-AT/star.png';
import arrow from './images-AT/Arrow.png';
import { flightData } from './data/FlightData';

const AtFlightPage = () => {
  return (
    <div className="at-airport-page">
      <div className="aero-telcel">AeroTelcel</div>


      {/* ICON HURT */}
      <div className="full-hurt-1">
        <a href="/ATFlightPage">
          <div className="buttonW">
            <img src={heart} alt="heart" />
          </div>
        </a>
      </div>


      {/* Arrow*/}
      <div className="arrow">
            <img src={arrow} alt="arrow" />
      </div>
     
      <div class="aero-telcel">AeroTelcel</div>
      <div class="flight">Flight:</div>

      <div class= "rectangle-f0"></div>
      <div class="flight-operator">Flight Operator:</div>
      <div class="expected-departure">Expected Departure:</div>
      <div class="expected-arrival">Expected Arrival:</div>

      <div class= "rectangle-f1"></div>
      <div class="departure">Departure</div>
      <div class="city2">City:</div>
      <div class="airport2">Airport:</div>
      <div class="conditions2">Conditions:</div>
  
  
      <div class= "rectangle-f2"></div>
      <div class="arrival">Arrival</div>
      <div class="city">City:</div>
      <div class="airport">Airport:</div>
      <div class="conditions">Conditions:</div>
      
      
      
      <div class= "rectangle-f3"></div>
      <div class="expected-delay-departure">Expected Delay Departure:</div>

      <div class= "rectangle-f4"></div>
      <div class="expected-delay-arrival">Expected Delay Arrival:</div>
     
      {/* Information that needs to be changed with GET */}
       
      <div class="ex-2024">{flightData.flightCode}</div>
      <div class="pt-1">{flightData.flightDepAirportCode}</div>
      <div class="pt-12">{flightData.flightArrAirportCode}</div>
      <div class="exp-dep-i">{flightData.flightDepTime}</div>
      <div class="exp-arr-i">{flightData.flightArrTime}</div>
      <div class="flight-op-i">{flightData.flightAirline}</div>
      <div class="d-city">{flightData.flightDepCity}</div>
      <div class="d-airport">{flightData.fligthDepAirport}</div>
      <div class="d-conditions">Rainy</div>
      <div class="a-conditions">Rainy</div>
      <div class="a-airport">{flightData.flightArrAirport}</div>
      <div class="a-city">{flightData.flightArrCity}</div>
      <div class="exp-de-d">{flightData.flightDepExpDelay}</div>
      <div class="exp-de-a">{flightData.flightArrExpDelay}</div>


      {/* Image-1 as a button */}
      <div className="image-2-container">
        <a href="/ATBrowser">
          <div className="buttonW">
            <img src={logoImage} alt="logo" />
          </div>
        </a>
      </div>

      <div className="starF">
        <a href="/ATFlightPageRep">
          <div className="buttonW">
            <img src= {star} alt="star" />
          </div>
        </a>
      </div>
    </div>
  );
};

export default AtFlightPage;
