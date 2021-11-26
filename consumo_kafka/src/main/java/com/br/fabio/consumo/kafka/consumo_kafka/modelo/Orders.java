package com.br.fabio.consumo.kafka.consumo_kafka.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity
public class Orders{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double total;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.NOT_PROCESSED;
	private String description;
	
	public Orders(String name, double total, Status status, String description) {
		super();
		this.name = name;
		this.total = total;
		this.status = status;
		this.description = description;
	}

}
