// App.js
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
