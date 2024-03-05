import React from 'react';
import './AtAirportpage.css'; // Import your styles
import logoImage from  './images-AT/logo.png';
import heart from './images-AT/iconhurt.png';
import star from './images-AT/star.png';
import  { airportData }  from './data/AirportData.js';
import ReactMapboxGl, { Layer, Feature, Marker } from 'react-mapbox-gl';
import 'mapbox-gl/dist/mapbox-gl.css';



const Map = ReactMapboxGl({
  
  accessToken:
    'pk.eyJ1IjoianJhbWlyZXpkZCIsImEiOiJjbHQyc2RyZGcwMWZnMnFucnRrdzduOHI0In0.erBra6R5LrjhCQguPSVGuw'
});


const conversionLat = () => parseFloat(airportData[0].AirportLatitud);
const conversionLong = () => parseFloat(airportData[0].AirportLongitud);




const AtAirportPage = () => {
  return (
    <div className="at-airport-page">
      <div className="aero-telcel">AeroTelcel</div>
      <div className="rectangle-13"></div>
      <div className="rectangle-14"></div>
      <div className="rectangle-12"></div>

      {/* ICON HURT */}
      <div className="icon-hurt-1">
        <a href="/ATAirportpageSub">
          <div className="buttonW">
            <img src={heart} alt="heart" />
          </div>
        </a>
      </div>

      <div className="expected-delay-dep">Expected Delay Departure:</div>
      <div className="expected-delay-arr">Expected Delay Arrival:</div>
      <div className="temperature">Temperature:</div>
      <div className="airport-code">Airport code:</div>
      <div className="airport-name">Airport name:</div>
      <div className="city-ai">City:</div>
      <div className="conditions-ai">Conditions:</div>
      <div className="time">Time:</div>
      <div className="delay-info">Delay Information</div>
      <div className="airport-information">Airport Information</div>

      {/* Information that needs to be changed with GET */}
      <div className="name-i">{airportData[0].AirportName}</div>
      <div className="code-i">{airportData[0].AirportCode}</div>
      <div className="temperature-i">20ºC</div>
      <div className="city-i">{airportData[0].AirportCity}</div>
      <div className="conditions-i">{airportData[0].AirportExpextedWeather}</div>
      <div className="time-i">07:35</div>
      <div className="delay-information-a">{airportData[0].AirportExpectedDelayArr}</div>
      <div className="delay-information-d">{airportData[0].AirportExpectedDelayDep}</div>



      {/* API MAP*/}

      
      
      <Map
        style="mapbox://styles/mapbox/light-v10"
        center={[conversionLong(), conversionLat()]}
        zoom={[13.5]}
        containerStyle={{

          height: '700px',
          width: '1200px',
          left:  '1400px',
          top: '330px',
          position: 'absolute',
          
        }}
      >
        
        <Layer type="symbol" id = "Marker" layout={{ 'icon-image': 'marker-15' }}>

          <Marker>
          <Feature coordinates={[conversionLong(), conversionLat()]} />
          </Marker>
        </Layer>
      </Map>


      

      {/* Image-1 as a button */}
      <div className="image-2-container">
        <a href="/ATBrowser">
          <div className="buttonW">
            <img src={logoImage} alt="logo" />
          </div>
        </a>
      </div>

      <div className="star">
        <a href="/ATAirportPageRep">
          <div className="buttonW">
            <img src= {star} alt="star" />
          </div>
        </a>
      </div>
    </div>
  );
};

export default AtAirportPage;


