import React, {useEffect, useState} from 'react';
import './AtFlightPage.css'; // Import your styles
import logoImage from  './images-AT/logo.png';
import heart from './images-AT/iconhurt.png';
import star from './images-AT/star.png';
import arrow from './images-AT/Arrow.png';
import airplaneM from './images-AT/airplaneM.png';
import ReactMapboxGl from 'react-mapbox-gl';
import 'mapbox-gl/dist/mapbox-gl.css';
import {useLocation, useNavigate} from "react-router-dom";
import mapboxgl from "mapbox-gl";

const Map = ReactMapboxGl({
  
  accessToken:
    'pk.eyJ1IjoianJhbWlyZXpkZCIsImEiOiJjbHQyc2RyZGcwMWZnMnFucnRrdzduOHI0In0.erBra6R5LrjhCQguPSVGuw'
});

const AtFlightPage = () => {
    const location = useLocation();
    console.log(location);
    const { flightID } = location.state;
    const navigate = useNavigate();
    const [flightCode, setFlightCode] = useState(null);
    const [airline, setAirline] = useState(null);
    const [airportDCode, setAirportDCode] = useState(null);
    const [airportACode, setAirportACode] = useState(null);
    const [flightDTime, setFlightDTime] = useState(null);
    const [flightATime, setFlightATime] = useState(null);
    const [flightDName, setFlightDName] = useState(null);
    const [flightAName, setFlightAName] = useState(null);
    const [flightDDelay, setFlightDDelay] = useState(null);
    const [flightDLat, setFlightDLat] = useState(null);
    const [flightDLong, setFlightDLong] = useState(null);
    const [flightADelay, setFlightADelay] = useState(null);
    const [flightALat, setFlightALat] = useState(null);
    const [flightALong, setFlightALong] = useState(null);
    const [flightLat, setFlightLat] = useState(null);
    const [flightLong, setFlightLong] = useState(null);



    const getFlightByCallsign = async (flightID) => {
        try {
            const response = await fetch(`http://localhost:100040/api/flightController/getFlightByCallsign/${flightID}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
                credentials: 'include',
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();

            setFlightCode(data.flightCode);
            setAirline(data.airline);
            setAirportDCode(data.flightDepAirportCode);
            setAirportACode(data.flightArrAirportCode); // Convert to float
            setFlightDTime(data.flightDepTime);
            setFlightATime(data.flightArrTime);
            setFlightDName(data.flightDepAirport);
            setFlightAName(data.flightArrAirport);
            setFlightDDelay(data.flightDepExpDelay);
            setFlightDLat(parseFloat(data.flightDepLat));
            setFlightDLong(parseFloat(data.flightDepLong));
            setFlightADelay(data.flightArrExpDelay);
            setFlightALat(parseFloat(data.flightArrLat));
            setFlightALong(parseFloat(data.flightArrLong));
            setFlightLat(data.flightLat);
            setFlightLong(data.flightLong);



            return data;
        } catch (error) {
            console.error('Error:', error);
        }
    };

  useEffect(() => {

      const fetchDataAndRenderMap = async () => {
          try {
              const data = await getFlightByCallsign(flightID);
              console.log(data);

              setFlightCode(data.flightCode);
              setAirline(data.airline);
              setAirportDCode(data.flightDepAirportCode);
              setAirportACode(data.flightArrAirportCode); // Convert to float
              setFlightDTime(data.flightDepTime);
              setFlightATime(data.flightArrTime);
              setFlightDName(data.flightDepAirport);
              setFlightAName(data.flightArrAirport);
              setFlightDDelay(data.flightDepExpDelay);
              setFlightDLat(parseFloat(data.flightDepLat));
              setFlightDLong(parseFloat(data.flightDepLong));
              setFlightADelay(data.flightArrExpDelay);
              setFlightALat(parseFloat(data.flightArrLat));
              setFlightALong(parseFloat(data.flightArrLong));
              setFlightLat(data.flightLat);
              setFlightLong(data.flightLong);

              const mapboxgl = require('mapbox-gl');
              mapboxgl.accessToken = 'pk.eyJ1IjoianJhbWlyZXpkZCIsImEiOiJjbHQyc2RyZGcwMWZnMnFucnRrdzduOHI0In0.erBra6R5LrjhCQguPSVGuw';

              const map = new mapboxgl.Map({
                  container: 'map', // container ID
                  style: 'mapbox://styles/mapbox/light-v10', // style URL
                  zoom: 10, // starting zoom
                  center: [flightLong , flightLat] // starting position
              });

              map.on('load', () => {
                  // Array of image objects with specific coordinates
                  const images = [
                      {
                          imageUrl: 'https://docs.mapbox.com/mapbox-gl-js/assets/custom_marker.png',
                          coordinates: [flightDLong, flightDLat],
                      },
                      {
                          imageUrl: 'https://docs.mapbox.com/mapbox-gl-js/assets/custom_marker.png',
                          coordinates: [flightALong(), flightALat()],
                      },
                      {
                          imageUrl: airplaneM,
                          coordinates: [flightLong, flightLat],
                      },
                      // Add more image objects with specific coordinates
                  ];

                  images.forEach(({ imageUrl, coordinates }, index) => {
                      map.loadImage(imageUrl, (error, image) => {
                          if (error) throw error;

                          // Add the image to the map style.
                          map.addImage(`marker-${index}`, image);

                          // Add a data source containing one point feature.
                          map.addSource(`point-${index}`, {
                              'type': 'geojson',
                              'data': {
                                  'type': 'FeatureCollection',
                                  'features': [
                                      {
                                          'type': 'Feature',
                                          'geometry': {
                                              'type': 'Point',
                                              'coordinates': coordinates,
                                          }
                                      }
                                  ]
                              }
                          });

                          // Add a layer to use the image to represent the data.
                          map.addLayer({
                              'id': `points-${index}`,
                              'type': 'symbol',
                              'source': `point-${index}`, // reference the data source
                              'layout': {
                                  'icon-image': `marker-${index}`, // reference the image
                                  'icon-size': 1.25
                              },
                          });
                      });
                  });
              });
          } catch (error) {
                  console.error('Error:', error);
              }
          };

          fetchDataAndRenderMap();
      }, [flightID]);



  return (
    <div className="at-airport-page">
      <div className="aero-telcel">AeroTelcel</div>


      {/* ICON HURT */}
      <div className="full-hurt-1">

          <div className="buttonW" onClick={()=>{navigate('/ATFlightSub', {replace: true, state: {flightID}})}}>
            <img src={heart} alt="heart" />
          </div>

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
      
      <div id="map" style={{  height: '700px',
          width: '1200px',
          left:  '1700px',
          top: '330px',
          position: 'absolute',}}>
            
          </div>
      
     
      {/* Information that needs to be changed with GET */}
       
        <div class="ex-2024">{flightCode}</div>
        <div class="pt-1">{airportDCode}</div>
        <div class="pt-12">{airportACode}</div>
        <div class="exp-dep-i">{flightDTime}</div>
        <div class="exp-arr-i">{flightATime}</div>
        <div class="flight-op-i">{airline}</div>
        <div class="d-city">{airportDCode}</div>
        <div class="d-airport">{flightDName}</div>
        <div class="d-conditions">Rainy</div>
        <div class="a-conditions">Rainy</div>
        <div class="a-airport">{flightAName}</div>
        <div class="a-city">{airportACode}</div>
        <div class="exp-de-d">{flightDDelay}</div>
        <div class="exp-de-a">{flightADelay}</div>


        

      {/* Image-1 as a button */}
      <div className="image-2-container">
        <a href="/ATBrowser">
          <div className="buttonW">
            <img src={logoImage} alt="logo" />
          </div>
        </a>
      </div>

      <div className="starF">
          <div className="buttonW" onClick={()=>{navigate('/ATFlightPageRep', {replace: true, state: {flightID}})}}>
            <img src= {star} alt="star" />
          </div>
      </div>
    </div>
  );
};

export default AtFlightPage;
