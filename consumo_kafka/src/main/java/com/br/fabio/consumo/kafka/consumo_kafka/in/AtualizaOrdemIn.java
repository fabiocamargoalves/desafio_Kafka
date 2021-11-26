package com.br.fabio.consumo.kafka.consumo_kafka.in;

import org.springframework.lang.NonNull;

import com.br.fabio.consumo.kafka.consumo_kafka.modelo.Orders;
import com.br.fabio.consumo.kafka.consumo_kafka.modelo.Status;
import com.br.fabio.consumo.kafka.consumo_kafka.repository.OrdemRepository;

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
public class AtualizaOrdemIn {
	
	@NonNull
	private String name;
	
	@NonNull
	private double total;
	
	@NonNull
	private Status status;
	
	@NonNull
	private String description;
	

	public Orders atualizar(Long id,OrdemRepository ordemRepository) {
		
		 Orders ordem = ordemRepository.getOne(id);
		 
		 ordem.setName(this.name); 
		 ordem.setStatus(Status.PROCESSED);
		 ordem.setTotal(this.total);
		 
		 return ordem;
		
	}
	
}
