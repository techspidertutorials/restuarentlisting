package com.techspider.restuarentlisting.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.techspider.restuarentlisting.dto.RestuarentDTO;
import com.techspider.restuarentlisting.entity.Restuarent;
import com.techspider.restuarentlisting.mapper.RestuarentMapper;
import com.techspider.restuarentlisting.repository.RestuarentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestuarentService {

	private RestuarentRepository restuarentRepository;
	
	public List<RestuarentDTO> findAllRestuarents() {
		
		List<Restuarent> allRestuarent=restuarentRepository.findAll();
		
		List<RestuarentDTO> allRestuarentDTO=allRestuarent.stream().map(restuarent->
				RestuarentMapper.INSTANCE.mapToRestuarentDTO(restuarent)
				).toList();

		return allRestuarentDTO;
	}

	public RestuarentDTO addNewRestuarent(RestuarentDTO restuarentDTO) {
		Restuarent restuarent=RestuarentMapper.INSTANCE.mapToRestuarent(restuarentDTO);
		Restuarent newRestuarent=restuarentRepository.save(restuarent);
		return RestuarentMapper.INSTANCE.mapToRestuarentDTO(newRestuarent);
	}

	public ResponseEntity<RestuarentDTO> getRestuarentById(Integer id)
	{
		Optional<Restuarent> restuarent=restuarentRepository.findById(id);
		if(restuarent.isPresent())
		{
			return new ResponseEntity<>(RestuarentMapper.INSTANCE.mapToRestuarentDTO(restuarent.get()),HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
}
