package com.br.fabio.api.kafka.api_kafka.aplication.form;

import org.springframework.lang.NonNull;

import com.br.fabio.api.kafka.api_kafka.aplication.modelo.Ordem;
import com.br.fabio.api.kafka.api_kafka.aplication.modelo.Status;
import com.br.fabio.api.kafka.api_kafka.aplication.repository.OrdemRepository;


public class AtualizaOrdemForm {
	
	@NonNull
	private String name;
	
	@NonNull
	private double total;
	
	@NonNull
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
	
	
	public Ordem atualizar(Long id, OrdemRepository ordemRepository) {
		
		Ordem ordem = ordemRepository.getOne(id);
		
		ordem.setName(this.name);
		ordem.setStatus(this.status);
		ordem.setTotal(this.total);
		
		return ordem;
	}
	
}
