import React from "react";

export const TableRow = ({flightList}) => {
    return (
    <tr>
        <td>
        <a href="/ATFlightPage">{flightList.flightId}</a>
        </td>
        <td>{flightList.airline}</td>
        <td>{flightList.departureTime}</td>
        <td>{flightList.departureCity}</td>
        <td>{flightList.arrivalCity}</td>
    </tr>    
    );
}
