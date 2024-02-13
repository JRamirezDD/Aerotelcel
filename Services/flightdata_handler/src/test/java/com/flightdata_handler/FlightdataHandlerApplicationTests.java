package com.flightdata_handler;

import com.flightdata_handler.service.*;
import com.flightdata_handler.controller.*;


import com.flightdata_handler.controller.FlightDataController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class FlightdataHandlerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FlightDataController flightDataController;

	@MockBean
	private ReadAllStates readAllStates;

	@Test
	void allStatesTest() {
		System.out.println("Performing all states test: \n");

		try{
			this.mockMvc.perform(MockMvcRequestBuilders.post("/api/flightController/updateAllStates"))
					.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e){
			System.out.println("We fucked up, exception: " + e);
		}
	}

}
