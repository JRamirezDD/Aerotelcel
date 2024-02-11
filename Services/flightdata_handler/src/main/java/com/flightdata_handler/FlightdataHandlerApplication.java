package com.flightdata_handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;

@SpringBootApplication
public class FlightdataHandlerApplication {

	Connection conn;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(FlightdataHandlerApplication.class, args);

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/openskytestdb", "root", "root");
		Upload updateFlights = new uploadFlights(conn);

		if(conn != null){
			System.out.println("Connected to DB");
		}
		else{
			System.out.println("Not connected to DB");
		}

		updateFlights.doSearch();
	}

}
