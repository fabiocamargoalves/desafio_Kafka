package com.br.fabio.api.kafka.api_kafka.aplication.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.br.fabio.api.kafka.api_kafka.aplication.modelo.Ordem;
import com.br.fabio.api.kafka.api_kafka.aplication.modelo.Status;


public class OrdemDto {
	
	private Long id;
	private String name;
	private double total;
	private Status status;
	
	public OrdemDto(Ordem ordem) {
		this.id = ordem.getId();
		this.name = ordem.getName();
		this.total = ordem.getTotal();
		this.status = ordem.getStatus();
	}
	
	public OrdemDto() {
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static List<OrdemDto> converter(List<Ordem> ordens) {
		
		return ordens.stream().map(OrdemDto::new).collect(Collectors.toList()); 
	}
	
}
