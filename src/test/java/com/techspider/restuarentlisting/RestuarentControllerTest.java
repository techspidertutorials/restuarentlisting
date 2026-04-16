package com.techspider.restuarentlisting;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.techspider.restuarentlisting.controller.RestuarentController;
import com.techspider.restuarentlisting.dto.RestuarentDTO;
import com.techspider.restuarentlisting.service.RestuarentService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RestuarentControllerTest {

	@InjectMocks
	private RestuarentController restuarentController;

	@Mock
	private RestuarentService restuarentService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllRestuarents() {

		// Mocking the service layer to return a list of RestuarentDTOs
		List<RestuarentDTO> mockRestuarents = new ArrayList<>();
		mockRestuarents.add(new RestuarentDTO(1, "Taj GateWay", "Vijayawada", "MG Road", "Best in town"));
		mockRestuarents.add(new RestuarentDTO(2, "Restuarent B", "Hyderabad", "Ameerpet", "Delicious food"));

		when(restuarentService.findAllRestuarents()).thenReturn(mockRestuarents);

		// Calling the controller method
		ResponseEntity<List<RestuarentDTO>> result = restuarentController.fetchAllRestuarents();

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(mockRestuarents, result.getBody());

		verify(restuarentService, times(1)).findAllRestuarents();

	}

	@Test
	public void testAddNewRestuarent() {

		RestuarentDTO savedRestuarent = new RestuarentDTO(1, "New Restuarent", "City", "Address", "Description");

		when(restuarentService.addNewRestuarent(savedRestuarent)).thenReturn(savedRestuarent);

		ResponseEntity<RestuarentDTO> result = restuarentController.saveRestuarent(savedRestuarent);

		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		assertEquals(savedRestuarent, result.getBody());

		verify(restuarentService, times(1)).addNewRestuarent(savedRestuarent);
	}

	@Test
	public void testfindRestuarentById() {
		Integer resturantId = 1;
		RestuarentDTO restuarent = new RestuarentDTO(1, "Taj Gateway", "vijayawada", "MG Road",
				"Best Hotel in vijayawada");

		when(restuarentService.getRestuarentById(resturantId))
				.thenReturn(new ResponseEntity<>(restuarent, HttpStatus.OK));

		ResponseEntity<RestuarentDTO> response = restuarentController.fetchRestuarentById(resturantId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(restuarent, response.getBody());

		verify(restuarentService,times(1)).getRestuarentById(resturantId);
	}
}