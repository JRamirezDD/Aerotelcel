export const airportData = [
    {
        // get from airports
        AirportName: 'Mexico City International Airport',
        AirportCode: 'MEX', //ask airport IATA for this
        AirportCity: 'Mexico City',
        AirportLatitud: '19.4363',
        AirportLongitud: '-99.0721',
        AirportCountry: 'Mexico',
        AirportCountryCode: 'MX', //this for curerent time
        AirportExpextedWeather: 'Sunny',
        AirportExpectedDelayDep: '+9',
        AirportExpectedDelayArr: '+5',

    },
    
]

// GET example
fetch('http://your-dns-endpoint', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },
})
.then(response => {
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
})
.then(data => console.log(data))
.catch(error => console.error('Error:', error));