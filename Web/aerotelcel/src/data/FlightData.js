



    export const flightData = {
      flightCode: 'AMX0001',
      flightAirline: 'Aeromexico',
      flightDepAirportCode: 'MEX',    //ask airport IATA for this
      flightArrAirportCode: 'JFK',    //ask airport IATA for this
      flightDepTime: '10:00',         //ask to airport for this x referencing the flightId
      flightArrTime: '14:00',         //ask to airport for this x referencing the flightId
      flightDepCity: 'Mexico City',
      fligthDepAirport: 'Mexico City International Airport',  //ask airport name for this
      flightArrCity: 'New York',                              //ask airport city for this
      flightArrAirport: 'John F. Kennedy International Airport',  //ask airport name for this
      flightDepExpDelay: '+5',
      flightDepLat: '19.4363',                                //ask airport latitud for this
      flightDepLong: '-99.0721',                              //ask airport longitud for this
      flightArrExpDelay: '-3',
      flightArrLat: '40.6413',                                //ask airport latitud for this
      flightArrLong: '-73.7781',                              //ask airport longitud for this
      flightLat: '30.4409',
      flightLong: '-87.890',
  };

    /*
        function getFlightByCallsign(callsign) {
    const currentLength = callsign.length;

    if (currentLength < 8) {
        const additionalCharacters = 8 - currentLength;
        const padding = Array(additionalCharacters).fill('%20').join('');
        callsign += padding;
    } else if (currentLength > 8) {
        // If the length is greater than 8, truncate the callsign
        callsign = callsign.slice(0, 8);
    }

    fetch(`http://localhost:8181/api/flightController/getFlightByCallsign/${callsign}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));
}

// Example usage
getFlightByCallsign("AAL1261");
    */