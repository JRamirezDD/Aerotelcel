import React, {useState} from 'react';
import { useLocation , useNavigate } from 'react-router-dom';
import './AtAirportpage.css'; // Import your styles
import logoImage from  './images-AT/logo.png';
import heart from './images-AT/iconhurt.png';
import star from './images-AT/star.png';
import  { airportData }  from './data/AirportData.js';

const ATFlightPage = () => {
    const location = useLocation();
    console.log(location);
    const { flightID } = location.state;
    const navigate = useNavigate();

    const [input1, setInput1] = useState('');
    const [input2, setInput2] = useState('');

    const postDataDynamic = ( email, flightID) => {
        // Replace this URL with the actual API endpoint for POST requests
        const dsnEndpoint = 'http://localhost:10002/api/subscription-handler/subscriptions/unsubscribe';

        // Data to be sent in the POST request
        const postData = {
            email: email,
            aviationDataID: flightID,
            // Add any additional properties as needed
        };

        // Make a POST request
        return fetch(dsnEndpoint, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(postData),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`POST request failed! Status: ${response.status}`);
                }
                if (response.headers.get('content-length') === '0') {
                    return null; // or handle it in a way that makes sense for your application
                }
                return response.json(); // Parse the response body as JSON
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

    const handleButtonClick = () => {
        // Perform the POST request with input1, input2, and IATA
        console.log('Name:', input1);
        console.log('E-mail:', input2);
        console.log('IATA:', flightID);

        postDataDynamic(input2, flightID);
        // After the POST request, navigate to another web page
        navigate('/ATFlightPage', {replace: true, state: {flightID}});
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

                <div className="button-sa" onClick={handleButtonClick}>
                    Submit
                </div>

            </div>

            <div className="rectangle rectangle-12-container-sa">

                <div className="buttonR-sa" onClick={()=>navigate('/ATFlightPageFav', {replace: true, state: {flightID}})}>
                    Cancel
                </div>

            </div>
            <div className="do-you-want-to-receive-notifications-about-this-airport-status-if-so-put-your-e-mail-in-the-box-down-to-receive-the-notifications">
                Do you want to stop receiving notifications about this flight status? If so, put your email.
            </div>
            <div className="name-sa">Name:</div>
            <div className="e-mail-sa">E-mail:</div>

        </div>
    );
};

export default ATFlightPage;
