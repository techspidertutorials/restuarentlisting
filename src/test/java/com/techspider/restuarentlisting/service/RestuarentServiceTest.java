package com.techspider.restuarentlisting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.techspider.restuarentlisting.dto.RestuarentDTO;
import com.techspider.restuarentlisting.entity.Restuarent;
import com.techspider.restuarentlisting.mapper.RestuarentMapper;
import com.techspider.restuarentlisting.repository.RestuarentRepository;

import jakarta.inject.Inject;

public class RestuarentServiceTest {
	@InjectMocks
	private RestuarentService restuarentService;

	@Mock
	private RestuarentRepository restuarentRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindAllRestuarents() {
		// create
		List<Restuarent> restuarentList = new ArrayList<>();
		restuarentList.add(new Restuarent(1, "Taj Gateway", "MG Road", "vijayawada", "best restuarent"));
		restuarentList.add(new Restuarent(2, "Taj Runway", "MG Road", "vizag", "best restuarent"));

		// run
		when(restuarentRepository.findAll()).thenReturn(restuarentList);

		List<RestuarentDTO> restuarentDTOList = restuarentService.findAllRestuarents();

		assertEquals(restuarentList.size(), restuarentDTOList.size());

		for (int i = 0; i < restuarentList.size(); i++) {
			RestuarentDTO restuarentDTO = RestuarentMapper.INSTANCE.mapToRestuarentDTO(restuarentList.get(i));
			assertEquals(restuarentDTO, restuarentDTOList.get(i));
		}

		verify(restuarentRepository, times(1)).findAll();
	}

	@Test
	public void testAddNewRestuarent() {
		RestuarentDTO mockRestuarentDTO = new RestuarentDTO(1, "Taj Gateway", "MG Road", "vijayawada", "Best Hotel");
		Restuarent mockRestuarent = RestuarentMapper.INSTANCE.mapToRestuarent(mockRestuarentDTO);

		when(restuarentRepository.save(mockRestuarent)).thenReturn(mockRestuarent);

		RestuarentDTO savedRestuarentDTO = restuarentService.addNewRestuarent(mockRestuarentDTO);

		assertEquals(mockRestuarentDTO, savedRestuarentDTO);

		verify(restuarentRepository, times(1)).save(mockRestuarent);

	}

	@Test
	public void testFetchRestuarentById_ExistingId() {
		Integer mockRestuarentId = 1;

		Restuarent mockRestuarent = new Restuarent(1, "Taj gateway", "Mg Road", "vijaywada", "best hotels");

		when(restuarentRepository.findById(mockRestuarentId)).thenReturn(Optional.of(mockRestuarent));

		ResponseEntity<RestuarentDTO> response = restuarentService.getRestuarentById(mockRestuarentId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockRestuarentId, response.getBody().getId());
		
		
		verify(restuarentRepository,times(1)).findById(mockRestuarentId);
		
	}
	
	@Test
	public void testFetchRestuarentById_NONExistingId() {
		Integer mockRestuarentId = 1;

		Restuarent mockRestuarent = new Restuarent(1, "Taj gateway", "Mg Road", "vijaywada", "best hotels");

		when(restuarentRepository.findById(mockRestuarentId)).thenReturn(Optional.empty());

		ResponseEntity<RestuarentDTO> response = restuarentService.getRestuarentById(mockRestuarentId);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(null, response.getBody());
		
		
		verify(restuarentRepository,times(1)).findById(mockRestuarentId);
		
	}

}
