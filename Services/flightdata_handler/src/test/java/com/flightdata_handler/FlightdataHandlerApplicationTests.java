package com.flightdata_handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class FlightdataHandlerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void allStatesTest() {
		System.out.println("Performing all states test: \n");
		this.mockMvc.perform(get("/updateAllStates"));

	}

}
