package com.techspider.restuarentlisting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.techspider.restuarentlisting.dto.RestuarentDTO;
import com.techspider.restuarentlisting.entity.Restuarent;

@Mapper
public interface RestuarentMapper {

	RestuarentMapper INSTANCE = Mappers.getMapper(RestuarentMapper.class);

	RestuarentDTO mapToRestuarentDTO(Restuarent restuarent);

	Restuarent mapToRestuarent(RestuarentDTO restuarent);

}
