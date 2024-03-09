import React, {useState} from 'react';
import { useLocation , useNavigate } from 'react-router-dom';
import './AtAirportpage.css'; // Import your styles
import logoImage from  './images-AT/logo.png';
import heart from './images-AT/iconhurt.png';
import star from './images-AT/star.png';
import  { airportData }  from './data/AirportData.js';

const AtAirportPage = () => {
    const location = useLocation();
    console.log(location);
    const { IATA } = location.state;
    const navigate = useNavigate();

    const [input1, setInput1] = useState('');
    const [input2, setInput2] = useState('');

    const handleButtonClick = () => {
        // Perform the POST request with input1, input2, and IATA
        console.log('Name:', input1);
        console.log('E-mail:', input2);
        console.log('IATA:', IATA);
        fetch('http://localhost:10002/api/subscription-handler/subscription', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({name: input1, email: input2, aviationDataID: IATA}),
        })

        // After the POST request, navigate to another web page
        navigate('/ATAirportPageFav', {replace: true, state: {IATA}});
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
      <div className="rectangle-15-sa"></div>

      <div className="name-input-sa">
        <input className="input-text-sa" type="text" placeholder="Name" id="name-input-a"
               value={input1}
               onChange={(event) => setInput1(event.target.value)} />
      </div>
      <div className="email-input-sa">
        <input className="input-text-sa" type="text" placeholder="E-mail" id="email-input-a"
               value={input2}
               onChange={(event) => setInput2(event.target.value)}/>
      </div>
      <div className="rectangle rectangle-11-container-sa">

          <div className="button-sa" onClick={()=>navigate('/ATAirportPage', {replace: true, state: {IATA}})}>
            Submit
          </div>

      </div>

      <div className="rectangle rectangle-12-container-sa">

          <div className="buttonR-sa" onClick={handleButtonClick}>
            Cancel
          </div>

      </div>
      <div className="do-you-want-to-receive-notifications-about-this-airport-status-if-so-put-your-e-mail-in-the-box-down-to-receive-the-notifications">
        Do you want to receive notifications about this airport status? If so, put
        your e-mail in the box down to receive the notifications.
      </div>
      <div className="name-sa">Name:</div>
      <div className="e-mail-sa">E-mail:</div>

    </div>
  );
};

export default AtAirportPage;
