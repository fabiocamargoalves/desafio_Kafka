package com.br.fabio.consumo.kafka.consumo_kafka.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Ordem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double total;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.PEDIDO;

	public Ordem() {
		
	}
	
	
	
	public Ordem(String name, double total, Status status) {
		super();
		this.name = name;
		this.total = total;
		this.status = status;
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



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
