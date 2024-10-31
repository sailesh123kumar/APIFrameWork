package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

	private Integer id;
	private String title;
	private Double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Rating{
		private Double rate;
		private Integer count;
		
	}
	
}
