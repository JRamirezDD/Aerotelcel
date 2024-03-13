import React, { useState } from 'react';
import './AtFlightPage.css'; // Import your styles
import logoImage from  './images-AT/logo.png';
import heart from './images-AT/iconhurt.png';
import star from './images-AT/star.png';
import arrow from './images-AT/Arrow.png';
import { flightData } from './data/FlightData';
import {useLocation, useNavigate} from "react-router-dom";

const AtFlightPage = () => {
  const location = useLocation();
  console.log(location);
  const { flightID }  = location.state;
  const navigate = useNavigate();

  const [serviceRating, setServiceRating] = useState(null);
  const [cleanlinessRating, setCleanlinessRating] = useState(null);
  const [installationsRating, setInstallationsRating] = useState(null);

  const handleRatingChange = (question, rating) => {
    switch (question) {
      case 'service':
        setServiceRating(rating);
        break;
      case 'cleanliness':
        setCleanlinessRating(rating);
        break;
      case 'installations':
        setInstallationsRating(rating);
        break;
      default:
        break;
    }
  };

  const getAnswerStyle = (question, rating) => {
    switch (question) {
      case 'service':
        return serviceRating === rating ? 'selectedAnswer' : '';
      case 'cleanliness':
        return cleanlinessRating === rating ? 'selectedAnswer' : '';
      case 'installations':
        return installationsRating === rating ? 'selectedAnswer' : '';
      default:
        return '';
    }
  };

    const postDataDynamic = (serviceRating, cleanlinessRating, installationsRating, flightID) => {
        // Replace this URL with the actual API endpoint for POST requests
        const dsnEndpoint = 'http://localhost:10010/api/reports-handler/reports/flights';

        // Data to be sent in the POST request
        const postData = {
            SERVICE: serviceRating,
            CLEANLINESS: cleanlinessRating,
            PUNCTUALITY: installationsRating,
            aviationDataID: flightID,
            // Add any additional properties as needed
        };

        // Make a POST request
        return fetch(dsnEndpoint, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(postData),
        })
            .then(response => {

                if (response.headers.get('content-length') === '0') {
                    return null; // or handle it in a way that makes sense for your application
                }
                return null; // Parse the response body as JSON
            })
            .then(data => {
                console.log('POST response:', data);
                return data; // Return the parsed data
            })
            .catch(error => {
                console.error('POST request error:', error);
                throw error; // Re-throw the error to propagate it
            });
    };
  const sendAnswers = () => {
    // Implement your logic to send the answers
    console.log('Service Rating:', serviceRating);
    console.log('Cleanliness Rating:', cleanlinessRating);
    console.log('Installations Rating:', installationsRating);

      //change value of serviceRating from(1-5) to (ONE-FIVE)
      if(serviceRating === 1){
          let serviceRating = "ONE";
      }
      else if(serviceRating === 2){
          let serviceRating = "TWO";
      }
      else if(serviceRating === 3){
          let serviceRating = "THREE";
      }
      else if(serviceRating === 4){
          let serviceRating = "FOUR";
      }
      else if(serviceRating === 5){
          let serviceRating = "FIVE";
      }

      //change value of serviceRating from(1-5) to (ONE-FIVE)
      if(cleanlinessRating === 1){
          let cleanlinessRating = "ONE";
      }
      else if(cleanlinessRating === 2){
          let cleanlinessRating = "TWO";
      }
      else if(cleanlinessRating === 3){
          let cleanlinessRating = "THREE";
      }
      else if(cleanlinessRating === 4){
          let cleanlinessRating = "FOUR";
      }
      else if(cleanlinessRating === 5){
          let cleanlinessRating = "FIVE";
      }


      //change value of serviceRating from(1-5) to (ONE-FIVE)
      if(installationsRating === 1){
          let installationsRating = "ONE";
      }
      else if(installationsRating === 2){
          let installationsRating = "TWO";
      }
      else if(installationsRating === 3){
          let installationsRating = "THREE";
      }
      else if(installationsRating === 4){
          let installationsRating = "FOUR";
      }
      else if(installationsRating === 5){
          let installationsRating = "FIVE";
      }

      postDataDynamic(serviceRating, cleanlinessRating, installationsRating, flightID);

      // After the POST request, navigate to another web page
      navigate('/ATFlightPage', {replace: true, state: {flightID}});
  };

  return (
    <div className="at-airport-page">
      <div className="aero-telcel">AeroTelcel</div>


      {/* ICON HURT */}
      <div className="full-hurt-1">
        <a href="/ATBrowser">
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
      <div class="d-airport">{flightData.flightDepAirport}</div>
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
        <a href="/ATBrowser">
          <div className="buttonW">
            <img src= {star} alt="star" />
          </div>
        </a>
      </div>
      <div className="blur-sf"></div>
      <div className="rectangle-16-sf"></div>

      <div>
      <div className="msgrep-f">
          Answer the following questions. The range of the answers is from 1-5. 1 being the lowest and 5 being the highest.
        </div>

        <div className="question1-f">
          <p className="qtext">How was the service of the flight?</p>
          <ul>
            {[1, 2, 3, 4, 5].map((rating) => (
              <li
                key={rating}
                className={`buttonsrep-f ${getAnswerStyle('service', rating)}`}
                onClick={() => handleRatingChange('service', rating)}
              >
                {rating}
              </li>
            ))}
          </ul>
        </div>

        <div className="question2-f">
          <p className="qtext-f">How was the cleanliness of the flight?</p>
          <ul>
            {[1, 2, 3, 4, 5].map((rating) => (
              <li
                key={rating}
                className={`buttonsrep-f ${getAnswerStyle('cleanliness', rating)}`}
                onClick={() => handleRatingChange('cleanliness', rating)}
              >
                {rating}
              </li>
            ))}
          </ul>
        </div>

        <div className="question3-f">
          <p className="qtext-f">How was the punctuality of the flight?</p>
          <ul>
            {[1, 2, 3, 4, 5].map((rating) => (
              <li
                key={rating}
                className={`buttonsrep-f ${getAnswerStyle('installations', rating)}`}
                onClick={() => handleRatingChange('installations', rating)}
              >
                {rating}
              </li>
            ))}
          </ul>
        </div>

        <div >
          <button className="buttonRAns-f" onClick={sendAnswers}>
            Send 
          </button>
        </div>
        <div >
          <a href="/ATFlightPage">
          <button className="buttonRCan-f"  onClick={() => navigate('/ATFlightPage', {replace: true, state: {flightID}})}>
            Cancel
          </button>
          </a>
        </div>
      </div>
    </div>
  );
};

export default AtFlightPage;
