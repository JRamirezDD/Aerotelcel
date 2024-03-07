// Mock data for the flight details
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
    
   function getFlightData() {
    // Replace "your-server-endpoint" with the actual URL of your server API
    const serverEndpoint = "https://your-server.com/api/data";

    // Make an HTTP GET request to the server endpoint
    fetch(serverEndpoint)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log("Received data from server:", data);
            // Handle the data here
        })
        .catch(error => {
            console.error("Error fetching data from server:", error);
        });   
    return flightData;
   }