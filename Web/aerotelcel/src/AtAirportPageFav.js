import React, {useEffect, useState} from 'react';
import {useLocation, useNavigate} from 'react-router-dom';
import './AtAirportpage.css'; // Import your styles
import logoImage from  './images-AT/logo.png';
import heart from './images-AT/fullhart.png';
import star from './images-AT/star.png';

import ReactMapboxGl from 'react-mapbox-gl';
import 'mapbox-gl/dist/mapbox-gl.css';
import mapboxgl from "mapbox-gl";


const Map = ReactMapboxGl({

    accessToken:
        'pk.eyJ1IjoianJhbWlyZXpkZCIsImEiOiJjbHQyc2RyZGcwMWZnMnFucnRrdzduOHI0In0.erBra6R5LrjhCQguPSVGuw'
});


const AtAirportPageFav = () => {
    const location = useLocation();
    console.log(location);
    const {IATA} = location.state;
    const navigate = useNavigate();
    const [iata, setIata] = useState(null);
    const [icao, setIcao] = useState(null);
    const [airportName, setAirportName] = useState(null);
    const [latitude, setLatitude] = useState(0);
    const [longitude, setLongitude] = useState(0);
    const [airportCoordinates, setAirportCoordinates] = useState([0, 0]);

    const getAirportByIata = async (IATA) => {

        try {
            const response = await fetch(`http://localhost:10020/api/airportDataController/getAirportByCode/${IATA}`, {
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

            setIata(data.iata);
            setIcao(data.icao);
            setAirportName(data.airport_name);
            setLatitude(parseFloat(data.latitude)); // Convert to float
            setLongitude(parseFloat(data.longitude)); // Convert to float

            return data;
        } catch (error) {
            console.error('Error:', error);
        }
    };

    useEffect(() => {
        const fetchDataAndRenderMap = async () => {
            try {
                const data = await getAirportByIata(IATA);
                console.log(data);

                // Convert latitude and longitude to float
                const newLatitude = parseFloat(data.latitude);
                const newLongitude = parseFloat(data.longitude);

                setIata(data.iata);
                setIcao(data.icao);
                setAirportName(data.airport_name);
                setLatitude(newLatitude);
                setLongitude(newLongitude);
            } catch (error) {
                console.error('Error:', error);
            }
        };

        fetchDataAndRenderMap();

        // Mapbox code
        const mapboxgl = require('mapbox-gl');
        mapboxgl.accessToken = 'pk.eyJ1IjoianJhbWlyZXpkZCIsImEiOiJjbHQyc2RyZGcwMWZnMnFucnRrdzduOHI0In0.erBra6R5LrjhCQguPSVGuw';

        const airportCoordinates = [longitude, latitude];

        const map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/mapbox/light-v10',
            zoom: 10,
            center: airportCoordinates,
        });


        console.log(airportCoordinates);

        map.on('load', () => {
            // Load an image from an external URL.
            map.loadImage(
                'https://docs.mapbox.com/mapbox-gl-js/assets/custom_marker.png',
                (error, image) => {
                    if (error) throw error;

                    // Add the image to the map style.
                    map.addImage('airportMarker', image);

                    // Add a data source containing one point feature.
                    map.addSource('point', {
                        type: 'geojson',
                        data: {
                            type: 'FeatureCollection',
                            features: [
                                {
                                    type: 'Feature',
                                    geometry: {
                                        type: 'Point',
                                        coordinates: airportCoordinates,
                                    },
                                },
                            ],
                        },
                    });

                    // Add a layer to use the image to represent the data.
                    map.addLayer({
                        id: 'points',
                        type: 'symbol',
                        source: 'point', // reference the data source
                        layout: {
                            'icon-image': 'airportMarker', // reference the image
                            'icon-size': 1.25,
                        },
                    });
                }
            );
        });
    }, [IATA, latitude, longitude]);

    return (
        <div className="at-airport-page">
            <div className="aero-telcel">AeroTelcel</div>
            <div className="rectangle-13"></div>
            <div className="rectangle-14"></div>
            <div className="rectangle-12"></div>

            {/* ICON HURT */}
            <div className="icon-hurt-1">
                    <div className="buttonW" onClick={()=>{navigate('/ATAirportPageUnsub', {replace: true, state: {IATA}})}}>
                        <img src={heart} alt="heart" />
                    </div>
            </div>

            <div className="expected-delay-dep">Expected Delay Departure:</div>
            <div className="expected-delay-arr">Expected Delay Arrival:</div>
            <div className="temperature">Temperature:</div>
            <div className="airport-code">Airport code:</div>
            <div className="airport-name">Airport name:</div>
            <div className="city-ai">ICAO:</div>
            <div className="conditions-ai">Conditions:</div>
            <div className="time">Time:</div>
            <div className="delay-info">Delay Information</div>
            <div className="airport-information">Airport Information</div>

            {/* Information that needs to be changed with GET */}
            <div className="name-i">{airportName}</div>
            <div className="code-i">{iata}</div>
            <div className="temperature-i">20ºC</div>
            <div className="city-i">{icao}</div>
            <div className="conditions-i">Sunny</div>
            <div className="time-i">07:35</div>
            <div className="delay-information-a">TBA</div>
            <div className="delay-information-d">TBA</div>



            {/* API MAP*/}



            <div id="map" style={{  height: '700px',
                width: '1200px',
                left:  '1400px',
                top: '330px',
                position: 'absolute',}}>

            </div>


            {/* Image-1 as a button */}
            <div className="image-2-container">
                <a href="/ATBrowser">
                    <div className="buttonW">
                        <img src={logoImage} alt="logo" />
                    </div>
                </a>
            </div>

            <div className="star">
                <div className="buttonW" onClick={()=>{navigate('/ATAirportPageRep', {replace: true, state: {IATA}})}}>
                    <img src= {star} alt="star" />
                </div>
            </div>
        </div>
    );
};

export default AtAirportPageFav;


