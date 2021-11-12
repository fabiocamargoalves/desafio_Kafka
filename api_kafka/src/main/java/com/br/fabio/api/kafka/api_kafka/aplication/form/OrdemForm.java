package com.br.fabio.api.kafka.api_kafka.aplication.form;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.lang.NonNull;

import com.br.fabio.api.kafka.api_kafka.aplication.modelo.Ordem;
import com.br.fabio.api.kafka.api_kafka.aplication.modelo.Status;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdemForm {
	
	private Long id;
	
	@NonNull
	private String name;
	
	@NonNull
	private double total;
	
	
	private Status status;
	
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

		
	
	public Ordem converter() {
		
		status = Status.PEDIDO;
		
		return new Ordem(name, total, status);
	}
}
