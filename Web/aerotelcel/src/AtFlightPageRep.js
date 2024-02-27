import React, { useState } from 'react';
import './AtFlightPage.css'; // Import your styles
import logoImage from  './images-AT/logo.png';
import heart from './images-AT/iconhurt.png';
import star from './images-AT/star.png';
import arrow from './images-AT/Arrow.png';


const AtFlightPage = () => {
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
       
        <div class="ex-2024">EX2024</div>
        <div class="pt-1">MAD</div>
        <div class="pt-12">BCN</div>
        <div class="exp-dep-i">14:30</div>
        <div class="exp-arr-i">15:30</div>
        <div class="flight-op-i">Example</div>
        <div class="d-city">Madrid</div>
        <div class="d-airport">Adolfo Suarez</div>
        <div class="d-conditions">Rainy</div>
        <div class="a-conditions">Rainy</div>
        <div class="a-airport">Barcelona</div>
        <div class="a-city">Barcelona</div>
        <div class="exp-de-d">+5</div>
        <div class="exp-de-a">-3</div>


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
          <p className="qtext">How was the punctuality of the flight?</p>
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
          <p className="qtext-f">How was the service of the flight?</p>
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
          <button className="buttonRCan-f">
            Cancel
          </button>
          </a>
        </div>
      </div>
    </div>
  );
};

export default AtFlightPage;
