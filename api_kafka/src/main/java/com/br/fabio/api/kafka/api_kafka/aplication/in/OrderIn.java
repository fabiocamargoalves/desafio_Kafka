package com.br.fabio.api.kafka.api_kafka.aplication.in;

import org.springframework.lang.NonNull;

import com.br.fabio.api.kafka.api_kafka.aplication.model.Status;
import com.br.fabio.api.kafka.api_kafka.aplication.out.Orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
@Data
@Builder
public class OrderIn {
	
	private Long id;
	
	@NonNull
	private String name;
	
	@NonNull
	private double total;
	
	
	private Status status;
	
	@NonNull
	private String description;
	
	
	public Orders converter() {
		
		status = Status.NOT_PROCESSED;
		
		return new Orders(name, total, status, description);
	}
}
