package com.techspider.restuarentlisting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestuarentDTO {
	private Integer id;
	private String name;
	private String city;
	private String address;
	private String restuarentdescription;
}
