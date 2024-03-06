import React, {useEffect} from 'react';
import './AtFlightPage.css'; // Import your styles
import logoImage from  './images-AT/logo.png';
import heart from './images-AT/iconhurt.png';
import star from './images-AT/star.png';
import arrow from './images-AT/Arrow.png';
import airplaneM from './images-AT/airplaneM.png';
import { flightData } from './data/FlightData';
import ReactMapboxGl from 'react-mapbox-gl';
import 'mapbox-gl/dist/mapbox-gl.css';

const Map = ReactMapboxGl({
  
  accessToken:
    'pk.eyJ1IjoianJhbWlyZXpkZCIsImEiOiJjbHQyc2RyZGcwMWZnMnFucnRrdzduOHI0In0.erBra6R5LrjhCQguPSVGuw'
});

const conversionLatD = () => parseFloat(flightData.flightDepLat);
const conversionLongD = () => parseFloat(flightData.flightDepLong);

const conversionLatA = () => parseFloat(flightData.flightArrLat);
const conversionLongA = () => parseFloat(flightData.flightArrLong);


const conversionLat = () => parseFloat(flightData.flightLat);
const conversionLong = () => parseFloat(flightData.flightLong);


const AtFlightPage = () => {

  useEffect(() => {
    const mapboxgl = require('mapbox-gl');
    mapboxgl.accessToken = 'pk.eyJ1IjoianJhbWlyZXpkZCIsImEiOiJjbHQyc2RyZGcwMWZnMnFucnRrdzduOHI0In0.erBra6R5LrjhCQguPSVGuw';

    const map = new mapboxgl.Map({
      container: 'map', // container ID
      style: 'mapbox://styles/mapbox/light-v10', // style URL
      zoom: 10, // starting zoom
      center: [conversionLong(), conversionLat()] // starting position
    });

    map.on('load', () => {
      // Array of image objects with specific coordinates
      const images = [
        {
          imageUrl: 'https://docs.mapbox.com/mapbox-gl-js/assets/custom_marker.png',
          coordinates: [conversionLongD(), conversionLatD()],
        },
        {
          imageUrl: 'https://docs.mapbox.com/mapbox-gl-js/assets/custom_marker.png',
          coordinates: [conversionLongA(), conversionLatA()],
        },
        {
          imageUrl: airplaneM,
          coordinates: [conversionLong(), conversionLat()],
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
              'icon-size': 0.75
            }
          });
        });
      });
    });
  }, [conversionLat, conversionLong]);


  return (
    <div className="at-airport-page">
      <div className="aero-telcel">AeroTelcel</div>


      {/* ICON HURT */}
      <div className="full-hurt-1">
        <a href="/ATFlightPageSub">
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
      
      <div id="map" style={{  height: '700px',
          width: '1200px',
          left:  '1700px',
          top: '330px',
          position: 'absolute',}}>
            
          </div>
      
     
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
