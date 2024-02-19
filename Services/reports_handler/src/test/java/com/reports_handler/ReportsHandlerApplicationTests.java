package com.reports_handler;

import com.flightdata_handler.model.Airport;
import com.flightdata_handler.model.Flight;
import com.reports_handler.model.AirportReport;
import com.reports_handler.model.FlightReport;
import com.reports_handler.repository.AirportReportRepository;
import com.reports_handler.repository.FlightReportRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class ReportsHandlerApplicationTests {

	@Autowired
	private AirportReportRepository airportReportRepository;

	@Autowired
	private FlightReportRepository flightReportRepository;

	@Test
	public void testSaveAirportReport() {
		Airport airport = new Airport();
		// Set airport properties

		AirportReport airportReport = new AirportReport();
		airportReport.setAirport(airport);

		airportReport = airportReportRepository.save(airportReport);

		assertNotNull(airportReport.getReportId());
		assertNotNull(airportReport.getAirport());
		// Add more assertions as needed
	}

	@Test
	public void testSaveFlightReport() {
		Flight flight = new Flight();
		// Set flight properties

		FlightReport flightReport = new FlightReport();
		flightReport.setFlight(flight);

		flightReport = flightReportRepository.save(flightReport);

		assertNotNull(flightReport.getReportId());
		assertNotNull(flightReport.getFlight());
		// Add more assertions as needed
	}
}
