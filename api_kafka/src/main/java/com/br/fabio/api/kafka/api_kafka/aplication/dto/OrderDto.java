package com.br.fabio.api.kafka.api_kafka.aplication.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.br.fabio.api.kafka.api_kafka.aplication.model.Status;
import com.br.fabio.api.kafka.api_kafka.aplication.out.Orders;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","name","total","status","description"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("total")
	private double total;
	
	@JsonProperty("status")
	private Status status;

	@JsonProperty("description")
	private String description;
	
	public OrderDto(Orders ordem) {
		this.id = ordem.getId();
		this.name = ordem.getName();
		this.total = ordem.getTotal();
		this.status = ordem.getStatus();
		this.description = ordem.getDescription();
				
	}

	public static List<OrderDto> converter(List<Orders> ordens) {
		
		return ordens.stream().map(OrderDto::new).collect(Collectors.toList()); 
	}
	
}
