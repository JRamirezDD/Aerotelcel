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

  function askDataFlight() {

    // DNS endpoint for the GET request

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
  }

  //GET JSON function where airportCode is value from input text

  function getAirportData(airportCode) {
    //GET JSON from API
    //return JSON
}

// POST request example {se suscribe a el ID de vuelo o aeropuerto}
function postDataFlight() {
  // Get values from input fields
  let nameFlight;
  document.getElementById('name-input-f').addEventListener('input', function(e) {
    nameFlight = e.target.value;
});
  let emailFlight;
  document.getElementById('email-input-f').addEventListener('input', function(e) {
    emailFlight = e.target.value;
});


  // Data to be sent in the POST request
  const postData = {
    nameFlightJ: nameFlight,
    emailFlightJ: emailFlight,
   // dataFlightJ: dataFlight,
  };

  // Replace this URL with the actual API endpoint for POST requests
  const dsnEndpoint = 'C:/Users/mg929/OneDrive/Documentos/Year 2 SE/SE Group Project/Aerotelcel/Services/logic_gateway/src/main/java/com/logic_gateway/LogicGatewayApplication.java';

  // Make a POST request
  fetch(dsnEndpoint, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'name': nameFlight,
      'email': emailFlight, 
      'aviationDataID': dataFlight,  // Correcting header values
      // Add any additional headers as needed
    },
    body: JSON.stringify(postData),
  })
    .then(response => {
      if (!response.ok) {
        throw new Error(`POST request failed! Status: ${response.status}`);
      }
      return response.json();
    })
    .then(data => {
      console.log('POST response:', data);
      // Handle the response as needed
    })
    .catch(error => {
      console.error('POST request error:', error);
    });
}

function postAnswersFlight() {
  // Get values from input fields


  // Data to be sent in the POST request
  const postData = {
    
   // dataFlightJ: dataFlight,
  };

  // Replace this URL with the actual API endpoint for POST requests
  const dsnEndpoint = '';
  // Make a POST request
  fetch(dsnEndpoint, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
       // Correcting header values
      // Add any additional headers as needed
    },
    body: JSON.stringify(postData),
  })
    .then(response => {
      if (!response.ok) {
        throw new Error(`POST request failed! Status: ${response.status}`);
      }
      return response.json();
    })
    .then(data => {
      console.log('POST response:', data);
      // Handle the response as needed
    })
    .catch(error => {
      console.error('POST request error:', error);
    });
}

// POST request example
function postDataAirport() {
  // Get values from input fields
  let nameAirport;
  document.getElementById('name-input-a').addEventListener('input', function(e) {
    nameAirport = e.target.value;
});
  let emailAirport;
  document.getElementById('email-input-a').addEventListener('input', function(e) {
    emailAirport = e.target.value;
});

  // Data to be sent in the POST request
  const postData = {
    nameAirportJ: nameAirport,
    emailAirportJ: emailAirport,
    //dataAirportJ: dataAirport,
  };

  // Replace this URL with the actual API endpoint for POST requests
  const dsnEndpoint = '';

  // Make a POST request
  fetch(dsnEndpoint, {
    method: 'POST',
    headers: {
      'name': nameAirportJ,
      'email': emailAirportJ, 
      'aviationDataID': dataAirportJ, 
      // Add any additional headers as needed
    },
    body: JSON.stringify(postData),
  })
    .then(response => {
      if (!response.ok) {
        throw new Error(`POST request failed! Status: ${response.status}`);
      }
      return response.json();
    })
    .then(data => {
      console.log('POST response:', data);
      // Handle the response as needed
    })
    .catch(error => {
      console.error('POST request error:', error);
    });
}

function postAnswersAirport() {
  // Get values from input fields


  // Data to be sent in the POST request
  const postData = {
    
   // dataFlightJ: dataFlight,
  };

  // Replace this URL with the actual API endpoint for POST requests
  const dsnEndpoint = '';
  // Make a POST request
  fetch(dsnEndpoint, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
       // Correcting header values
      // Add any additional headers as needed
    },
    body: JSON.stringify(postData),
  })
    .then(response => {
      if (!response.ok) {
        throw new Error(`POST request failed! Status: ${response.status}`);
      }
      return response.json();
    })
    .then(data => {
      console.log('POST response:', data);
      // Handle the response as needed
    })
    .catch(error => {
      console.error('POST request error:', error);
    });
}