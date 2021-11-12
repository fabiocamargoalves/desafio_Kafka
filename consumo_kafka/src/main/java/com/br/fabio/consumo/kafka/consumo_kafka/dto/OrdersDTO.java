package com.br.fabio.consumo.kafka.consumo_kafka.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.br.fabio.consumo.kafka.consumo_kafka.modelo.Ordem;
import com.br.fabio.consumo.kafka.consumo_kafka.modelo.Status;


public class OrdersDTO {
	
	private Long id;
	private String name;
	private Status status;
	private double total;
	
	public OrdersDTO(Ordem ordem) {
		this.id = ordem.getId();
		this.name = ordem.getName();
		this.total = ordem.getTotal();
		this.status = ordem.getStatus();
	}
	
	public OrdersDTO() {
	}
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public static List<OrdersDTO> converter(List<Ordem> ordens) {
		
		return ordens.stream().map(OrdersDTO::new).collect(Collectors.toList()); 
	}
	
	
}
