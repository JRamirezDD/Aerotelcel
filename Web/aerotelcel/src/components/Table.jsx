import React from 'react';
import { doomydata } from './Doomydata.js';
import './Table.css'; // Import the CSS file
import { TableRow } from './TableRow.jsx';

export const Table = () => {
  return (
    <table>
      <thead>
        <tr>
          <th>Flight ID</th>
          <th>Airline</th>
          <th>Departure Time</th>
          <th>Departure City</th>
          <th>Arrival City</th>
        </tr>
      </thead>
      <tbody>
        {doomydata.map((flightList) => (
          <TableRow flightList={flightList}  />
        ))}
      </tbody>
    </table>
  );
};

