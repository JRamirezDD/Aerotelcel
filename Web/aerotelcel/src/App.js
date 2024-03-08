// App.js
import { Fragment } from 'react';
import { BrowserRouter, Routes, Route} from 'react-router-dom';
import ATBrowser from './ATBrowser.js';
import AtAirportPage from './AtAirportPage';
import AtAirportPageFav from './AtAirportPageFav';
import AtFlightPage from './AtFlightPage';
import AtFlightPageFav from './AtFlightPageFav';
import AtFlightPageSub from './AtFlightPageSub';
import AtFlightPageRep from './AtFlightPageRep';
import AtAirportPageSub from './AtAirportPageSub';
import AtAirportPageRep from './AtAirportPageRep';
import AtFlightList from './AtFlightList';

// Import cors
import cors from 'cors';

// Create an instance of cors middleware
const corsMiddleware = cors({
  origin: 'http://localhost:3000', // Add your client's origin here
  methods: 'GET,HEAD,PUT,PATCH,POST,DELETE',
  credentials: true,
  optionsSuccessStatus: 204,
});

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route
            path="/"
            element={
              // Wrap your components with the corsMiddleware
              <Fragment>
                {corsMiddleware}
                <ATBrowser />
              </Fragment>
            }
        />
        <Route path="/" element={<ATBrowser />} />  
        <Route path="/ATBrowser" element={<ATBrowser />} />
        <Route path="/ATFlightList" element={<AtFlightList/>} />

        <Route path="/ATAirportPage"    element={<AtAirportPage />} />
        <Route path="/ATAirportPageFav" element={<AtAirportPageFav />} />
        <Route path="/ATAirportPageRep" element={<AtAirportPageRep />} />
        <Route path="/ATAirportPageSub" element={<AtAirportPageSub />} />

        <Route path="/ATFlightPage"    element={<AtFlightPage />} />
        <Route path="/ATFlightPageFav" element={<AtFlightPageFav />} />
        <Route path="/ATFlightPageRep" element={<AtFlightPageRep />} />
        <Route path="/ATFlightPageSub" element={<AtFlightPageSub />} />
        
        <Route path="*" element={<ATBrowser />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
