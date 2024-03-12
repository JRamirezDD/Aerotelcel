import React from "react";
import {useNavigate} from "react-router-dom";

export const TableRow = ({flightList}) => {
    const navigate = useNavigate();
    const flightID = flightList.flightId;
    return (
    <tr>
        <td>
        <a onClick={() => navigate('/ATFlightPage', {replace: true, state: {flightID}})}>{flightList.flightId}</a>
        </td>
        <td>{flightList.airline}</td>
        <td>{flightList.departureTime}</td>
        <td>{flightList.departureCity}</td>
        <td>{flightList.arrivalCity}</td>
    </tr>    
    );
}
