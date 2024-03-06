import React, { useState } from 'react';
import './AtAirportpage.css'; // Import your styles
import logoImage from  './images-AT/logo.png';
import heart from './images-AT/iconhurt.png';
import star from './images-AT/star.png';
import  { airportData }  from './data/AirportData.js';

const AtAirportPage = () => {
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

  const sendAnswers = () => {
    // Implement your logic to send the answers
    console.log('Service Rating:', serviceRating);
    console.log('Cleanliness Rating:', cleanlinessRating);
    console.log('Installations Rating:', installationsRating);
  };

  return (
    <div className="at-airport-page">
      <div className="aero-telcel">AeroTelcel</div>
      <div className="rectangle-13"></div>
      <div className="rectangle-14"></div>
      <div className="rectangle-12"></div>

      {/* ICON HURT */}
      <div className="icon-hurt-1">
        <a href="/ATBrowser">
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

      {/* Image-1 as a button */}
      <div className="image-2-container">
        <a href="/ATBrowser">
          <div className="buttonW">
            <img src={logoImage} alt="logo" />
          </div>
        </a>
      </div>

      <div className="star">
        <a href="/ATBrowser">
          <div className="buttonW">
            <img src= {star} alt="star" />
          </div>
        </a>
      </div>

      <div className="blur-sa"></div>
      <div className="rectangle-16-sa"></div>

      <div>
      <div className="msgrep">
          Answer the following questions. The range of the answers is from 1-5. 1 being the lowest and 5 being the highest.
        </div>

        <div className="question1">
          <p className="qtext">How was the service of the airport?</p>
          <ul>
            {[1, 2, 3, 4, 5].map((rating) => (
              <li
                key={rating}
                className={`buttonsrep ${getAnswerStyle('service', rating)}`}
                onClick={() => handleRatingChange('service', rating)}
              >
                {rating}
              </li>
            ))}
          </ul>
        </div>

        <div className="question2">
          <p className="qtext">How was the cleanliness of the airport?</p>
          <ul>
            {[1, 2, 3, 4, 5].map((rating) => (
              <li
                key={rating}
                className={`buttonsrep ${getAnswerStyle('cleanliness', rating)}`}
                onClick={() => handleRatingChange('cleanliness', rating)}
              >
                {rating}
              </li>
            ))}
          </ul>
        </div>

        <div className="question3">
          <p className="qtext">How was the installations of the airport?</p>
          <ul>
            {[1, 2, 3, 4, 5].map((rating) => (
              <li
                key={rating}
                className={`buttonsrep ${getAnswerStyle('installations', rating)}`}
                onClick={() => handleRatingChange('installations', rating)}
              >
                {rating}
              </li>
            ))}
          </ul>
        </div>

        <div >
          <button className="buttonRAns" onClick={sendAnswers}>
            Send 
          </button>
        </div>
        <div >
          <a href="/ATAirportPage">
          <button className="buttonRCan">
            Cancel
          </button>
          </a>
        </div>
      </div>
    </div>
  );
};

export default AtAirportPage;
