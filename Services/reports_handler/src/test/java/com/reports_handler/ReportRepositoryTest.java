package com.reports_handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.flightdata_handler.model.Airport;
import com.flightdata_handler.model.Flight;
import com.reports_handler.model.AirportReport;
import com.reports_handler.model.FlightReport;
import com.reports_handler.repository.AirportReportRepository;
import com.reports_handler.repository.FlightReportRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
public class ReportRepositoryTest {

    @Autowired
    private AirportReportRepository airportReportRepository;

    @Autowired
    private FlightReportRepository flightReportRepository;

    @LocalServerPort
    private Integer port;

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"));

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