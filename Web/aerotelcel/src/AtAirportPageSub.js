import React from 'react';
import './AtAirportpage.css'; // Import your styles
import logoImage from  './images-AT/logo.png';
import heart from './images-AT/iconhurt.png';
import star from './images-AT/star.png';


const AtAirportPage = () => {
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
      <div className="name-i">Adolfo Suarez</div>
      <div className="code-i">MAD</div>
      <div className="temperature-i">14°C</div>
      <div className="city-i">Madrid</div>
      <div className="conditions-i">Rainy</div>
      <div className="time-i">14:20</div>
      <div className="delay-information-a">+5</div>
      <div className="delay-information-d">+9</div>

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
        <input className="input-text-sa" type="text" placeholder="Name" id="name-input-a" />
      </div>
      <div className="email-input-sa">
        <input className="input-text-sa" type="text" placeholder="E-mail" id="email-input-a" />
      </div>
      <div className="rectangle rectangle-11-container-sa">
        <a href="/ATAirportPageFav">
          <div className="button-sa">
            Submit
          </div>
        </a>
      </div>

      <div className="rectangle rectangle-12-container-sa">
        <a href="/ATAirportPage">
          <div className="buttonR-sa">
            Cancel
          </div>
        </a>
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
