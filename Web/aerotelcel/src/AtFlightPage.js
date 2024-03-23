/*
 *  Title: Flight Page for Aerotelcel
 *  Author: Marcos Gonzalez Fernandez
 *  Date: 23/03/2024
 *  Description: This is the flight page for the Aerotelcel web app
 *  Code Version: 1.0
 *  Availability: LZSCC-230: Software Engineer group project
 */

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
            const response = await fetch(`http://localhost:10020/api/flightController/getFlightByCallsign/${flightID}`, {
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
            setAirportACode(data.flightArrAirportCode);
            setFlightDTime(data.flightDepTime);
            setFlightATime(data.flightArrTime);
            setFlightDName(data.flightDepAirport);
            setFlightAName(data.flightArrAirport);
            setFlightDDelay(data.flightDepExpDelay);
            setFlightDLat(parseFloat(data.flightDepLatitude));
            setFlightDLong(parseFloat(data.flightDepLongitude));
            setFlightADelay(data.flightArrExpDelay);
            setFlightALat(parseFloat(data.flightArrLatitude));
            setFlightALong(parseFloat(data.flightArrLongitude));
            setFlightLat(data.flightLatitude);
            setFlightLong(data.flightLongitude);

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
                setAirportACode(data.flightArrAirportCode);
                setFlightDTime(data.flightDepTime);
                setFlightATime(data.flightArrTime);
                setFlightDName(data.flightDepAirport);
                setFlightAName(data.flightArrAirport);
                setFlightDDelay(data.flightDepExpDelay);
                setFlightDLat(parseFloat(data.flightDepLatitude));
                setFlightDLong(parseFloat(data.flightDepLongitude));
                setFlightADelay(data.flightArrExpDelay);
                setFlightALat(parseFloat(data.flightArrLatitude));
                setFlightALong(parseFloat(data.flightArrLongitude));
                setFlightLat(data.flightLatitude);
                setFlightLong(data.flightLongitude);

                const mapboxgl = require('mapbox-gl');
                mapboxgl.accessToken = 'pk.eyJ1IjoianJhbWlyZXpkZCIsImEiOiJjbHQyc2RyZGcwMWZnMnFucnRrdzduOHI0In0.erBra6R5LrjhCQguPSVGuw';

                const map = new mapboxgl.Map({
                    container: 'map', // container ID
                    style: 'mapbox://styles/mapbox/light-v10', // style URL
                    zoom: 10, // starting zoom
                    center: [flightLong, flightLat] // starting position
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
                            coordinates: [flightALong, flightALat],
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

                    // Add a line layer to connect departure and arrival airports
                    map.addLayer({
                        'id': 'flight-line',
                        'type': 'line',
                        'source': {
                            'type': 'geojson',
                            'data': {
                                'type': 'Feature',
                                'geometry': {
                                    'type': 'LineString',
                                    'coordinates': [
                                        [flightDLong, flightDLat],
                                        [flightALong, flightALat],
                                    ],
                                },
                            },
                        },
                        'layout': {
                            'line-join': 'round',
                            'line-cap': 'round',
                        },
                        'paint': {
                            'line-color': '#888',
                            'line-width': 2,
                        },
                    });
                });
            } catch (error) {
                console.error('Error:', error);
            }
        };

        fetchDataAndRenderMap();
    }, [flightID, flightLong, flightLat, flightDLong, flightDLat, flightALong, flightALat]);



  return (
    <div className="at-airport-page">
      <div className="aero-telcel">AeroTelcel</div>


      {/* ICON HURT */}
      <div className="full-hurt-1">

          <div className="buttonW" onClick={()=>{navigate('/ATFlightPageSub', {replace: true, state: {flightID}})}}>
            <img src={heart} alt="heart" />
          </div>

      </div>


      {/* Arrow*/}
      <div className="arrow">
            <img src={arrow} alt="arrow" />
      </div>
     
      <div className="aero-telcel">AeroTelcel</div>
      <div className="flight">Flight:</div>

      <div className= "rectangle-f0"></div>
      <div className="flight-operator">Flight Operator:</div>
      <div className="expected-departure">Expected Departure:</div>
      <div className="expected-arrival">Expected Arrival:</div>

      <div className= "rectangle-f1"></div>
      <div className="departure">Departure</div>
      <div className="city2">City:</div>
      <div className="airport2">Airport:</div>
      <div className="conditions2">Conditions:</div>
  
  
      <div className= "rectangle-f2"></div>
      <div className="arrival">Arrival</div>
      <div className="city">City:</div>
      <div className="airport">Airport:</div>
      <div className="conditions">Conditions:</div>
      
      
      
      <div className= "rectangle-f3"></div>
      <div className="expected-delay-departure">Expected Delay Departure:</div>

      <div className= "rectangle-f4"></div>
      <div className="expected-delay-arrival">Expected Delay Arrival:</div>
      
      <div id="map" style={{  height: '700px',
          width: '1200px',
          left:  '1700px',
          top: '330px',
          position: 'absolute',}}>
            
          </div>
      
     
      {/* Information that needs to be changed with GET */}
       
        <div className="ex-2024">{flightCode}</div>
        <div className="pt-1">{airportDCode}</div>
        <div className="pt-12">{airportACode}</div>
        <div className="exp-dep-i">{flightDTime}</div>
        <div className="exp-arr-i">{flightATime}</div>
        <div className="flight-op-i">{airline}</div>
        <div className="d-city">{airportDCode}</div>
        <div className="d-airport">{flightDName}</div>
        <div className="d-conditions">Rainy</div>
        <div className="a-conditions">Rainy</div>
        <div className="a-airport">{flightAName}</div>
        <div className="a-city">{airportACode}</div>
        <div className="exp-de-d">{flightDDelay}</div>
        <div className="exp-de-a">{flightADelay}</div>


        

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
