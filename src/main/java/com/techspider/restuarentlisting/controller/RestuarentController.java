package com.techspider.restuarentlisting.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techspider.restuarentlisting.dto.RestuarentDTO;
import com.techspider.restuarentlisting.service.RestuarentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/restuarent")
public class RestuarentController {

	private RestuarentService restuarentService;

	@GetMapping("/fetchAllRestuarents")
	public ResponseEntity<List<RestuarentDTO>> fetchAllRestuarents() {
		List<RestuarentDTO> allRestuarents = restuarentService.findAllRestuarents();
		return new ResponseEntity<>(allRestuarents, HttpStatus.OK);
	}
	
	@PostMapping("/addRestuarent")
	public ResponseEntity<RestuarentDTO> saveRestuarent(@RequestBody RestuarentDTO restuarentDTO)
	{
		RestuarentDTO restuarent=restuarentService.addNewRestuarent(restuarentDTO);
		return new ResponseEntity<>(restuarent,HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchRestuarent/{id}")
	public ResponseEntity<RestuarentDTO> fetchRestuarentById(@PathVariable Integer id)
	{
		return restuarentService.getRestuarentById(id);
	}
	
}
