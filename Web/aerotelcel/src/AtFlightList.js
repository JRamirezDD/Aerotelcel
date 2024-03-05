import React from 'react';
import './AtFlightList.css'; // Import your styles here
import logoImage from './images-AT/logo.png'; // Correct the path to the image
import { Table } from './components/Table';


const AtFlightList = () => {
  return (
    <div className="a-tbrowser">
      <div className="aero-telcel">AeroTelcel</div>



      {/* Image-1 as a button */}
      <div className="image-1-container">
        <a href="/ATBrowser">
          <div className="buttonW">
            <img src= {logoImage} alt="logo" />
          </div>
        </a>
      </div>



      <div className='tableFlights'>
        <Table />
     </div>
     
    </div>
  );
};

export default AtFlightList;
