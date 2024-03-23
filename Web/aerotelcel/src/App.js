/*
 *  Title: App Router for Aerotelcel
 *  Author: Marcos Gonzalez Fernandez
 *  Date: 23/03/2024
 *  Description: This is the main router for the Aerotelcel web app
 *  Code Version: 1.0
 *  Availability: LZSCC-230: Software Engineer group project
 */


import { Fragment } from 'react';
import { BrowserRouter, Routes, Route} from 'react-router-dom';
import ATBrowser from './ATBrowser.js';
import AtAirportPage from './AtAirportPage';
import AtAirportPageFav from './AtAirportPageFav';
import AtFlightPage from './AtFlightPage';
import AtFlightPageFav from './AtFlightPageFav';
import AtFlightPageSub from './AtFlightPageSub';
import AtFlightPageUnsub from './AtFlightPageUnsub';
import AtFlightPageRep from './AtFlightPageRep';
import AtAirportPageSub from './AtAirportPageSub';
import AtAirportPageUnsub from './AtAirportPageUnsub';
import AtAirportPageRep from './AtAirportPageRep';
import AtFlightList from './AtFlightList';

// Import cors


const App = () => {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<ATBrowser />} />  
        <Route path="/ATBrowser" element={<ATBrowser />} />
        <Route path="/ATFlightList" element={<AtFlightList/>} />

        <Route path="/ATAirportPage"    element={<AtAirportPage />} />
        <Route path="/ATAirportPageFav" element={<AtAirportPageFav />} />
        <Route path="/ATAirportPageRep" element={<AtAirportPageRep />} />
        <Route path="/ATAirportPageSub" element={<AtAirportPageSub />} />
        <Route path="/ATAirportPageUnsub" element={<AtAirportPageUnsub />} />

        <Route path="/ATFlightPage"    element={<AtFlightPage />} />
        <Route path="/ATFlightPageFav" element={<AtFlightPageFav />} />
        <Route path="/ATFlightPageRep" element={<AtFlightPageRep />} />
        <Route path="/ATFlightPageSub" element={<AtFlightPageSub />} />
        <Route path="/ATFlightPageUnsub" element={<AtFlightPageUnsub />} />
        
        <Route path="*" element={<ATBrowser />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
