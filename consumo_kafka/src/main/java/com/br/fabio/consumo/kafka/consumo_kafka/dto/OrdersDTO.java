package com.br.fabio.consumo.kafka.consumo_kafka.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.br.fabio.consumo.kafka.consumo_kafka.modelo.Orders;
import com.br.fabio.consumo.kafka.consumo_kafka.modelo.Status;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrdersDTO {
	
	private Long id;
	private String name;
	private Status status;
	private double total;
	private String description;
	
	public OrdersDTO(Orders ordem) {
		this.id = ordem.getId();
		this.name = ordem.getName();
		this.total = ordem.getTotal();
		this.status = ordem.getStatus();
		this.description = ordem.getDescription();
	}

	public static List<OrdersDTO> converter(List<Orders> ordens) {
		
		return ordens.stream().map(OrdersDTO::new).collect(Collectors.toList()); 
	}
	
	
}
