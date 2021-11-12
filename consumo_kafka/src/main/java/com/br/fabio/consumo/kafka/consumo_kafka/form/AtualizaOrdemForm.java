package com.br.fabio.consumo.kafka.consumo_kafka.form;

import org.springframework.lang.NonNull;

import com.br.fabio.consumo.kafka.consumo_kafka.modelo.Ordem;
import com.br.fabio.consumo.kafka.consumo_kafka.modelo.Status;
import com.br.fabio.consumo.kafka.consumo_kafka.repository.OrdemRepository;


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
	
	
	public Ordem atualizar(Long id,OrdemRepository ordemRepository) {
		
		 Ordem ordem = ordemRepository.getOne(id);
		 
		 ordem.setName(this.name); 
		 ordem.setStatus(Status.PROCESSADO);
		 ordem.setTotal(this.total);
		 
		 return ordem;
		
	}
	
}
