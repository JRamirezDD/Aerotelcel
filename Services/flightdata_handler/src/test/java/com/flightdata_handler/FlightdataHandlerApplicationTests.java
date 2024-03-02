package com.flightdata_handler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.flightdata_handler.controller.*;
import com.flightdata_handler.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;


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
	void allStatesTest(){
		System.out.println("Performing update all states test: \n");

		try{
			readAllStates.doSearch();
			//when(readAllStates.readPython()).thenReturn(true);


			this.mockMvc.perform(MockMvcRequestBuilders.put("/api/flightController/updateAllStates")).andExpect(MockMvcResultMatchers.status().isOk());

		} catch (Exception e){
			System.out.println("We messed up, exception: " + e);
		}
	}


	/*@Test
	void allStatesTest() {
		System.out.println("Performing update all states test: \n");
		try{
			this.mockMvc.perform(MockMvcRequestBuilders.put("/api/flightController/updateAllStates"))
					.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e){
			System.out.println("We fucked up, exception: " + e);
		}
	}*/
}
