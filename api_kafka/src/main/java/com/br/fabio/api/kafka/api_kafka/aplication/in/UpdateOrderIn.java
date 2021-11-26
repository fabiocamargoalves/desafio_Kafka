package com.br.fabio.api.kafka.api_kafka.aplication.in;

import org.springframework.lang.NonNull;

import com.br.fabio.api.kafka.api_kafka.aplication.model.Status;
import com.br.fabio.api.kafka.api_kafka.aplication.out.Orders;
import com.br.fabio.api.kafka.api_kafka.aplication.repository.OrderRepository;

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
public class UpdateOrderIn {
	
	@NonNull
	private String name;
	
	@NonNull
	private double total;
	
	@NonNull
	private Status status;
	
	@NonNull
	private String description;
	
	public Orders atualizar(Long id, OrderRepository ordemRepository) {
		
		Orders ordem = ordemRepository.getOne(id);
		
		ordem.setName(this.name);
		ordem.setStatus(this.status);
		ordem.setTotal(this.total);
		ordem.setDescription(this.description);
		
		return ordem;
	}
	
}
