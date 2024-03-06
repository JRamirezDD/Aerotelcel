import React from "react";
import { TableRow } from "./TableRow";

export const TableSection = ({flightList}) => {
    return <tbody>
        <tr>
        </tr>
        <TableRow flightList={flightList} />
    </tbody>;
}
