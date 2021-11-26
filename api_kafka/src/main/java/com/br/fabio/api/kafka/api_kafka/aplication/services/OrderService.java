package com.br.fabio.api.kafka.api_kafka.aplication.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.fabio.api.kafka.api_kafka.aplication.dto.OrderDto;
import com.br.fabio.api.kafka.api_kafka.aplication.in.OrderIn;
import com.br.fabio.api.kafka.api_kafka.aplication.in.UpdateOrderIn;
import com.br.fabio.api.kafka.api_kafka.aplication.out.Orders;
import com.br.fabio.api.kafka.api_kafka.aplication.produtor.OrderProducer;
import com.br.fabio.api.kafka.api_kafka.aplication.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderProducer orderProducer;
	
	
	public List<OrderDto> lista(String name){
		
		
		if (name == null) {
			List<Orders> ordens = orderRepository.findAll();
			return OrderDto.converter(ordens);
		}else {
			List<Orders> ordens = orderRepository.findByName(name);
			return OrderDto.converter(ordens);
		}
	}
	
	
	public List<OrderDto> findFilters(String q, double minTotal, double maxTotal){
			
		List<Orders> ordens = orderRepository.findFilters(q, minTotal, maxTotal);
		
		return OrderDto.converter(ordens);
	}
	
	
	public ResponseEntity<OrderDto> cadastrar(@RequestBody @Validated OrderIn orderIn, UriComponentsBuilder uriBuilder) {
		Orders ordem = orderIn.converter();
		orderRepository.save(ordem);
		URI uri = uriBuilder.path("/listaOrdens/{id}").buildAndExpand(ordem.getId()).toUri();
		
		orderProducer.send(orderIn);
		
		
		return ResponseEntity.created(uri).body(new OrderDto(ordem));
	}
	
	public ResponseEntity<OrderDto> detalhar(@PathVariable Long id) {
		Optional<Orders> ordem =  orderRepository.findById(id);
		if (ordem.isPresent()) {
			return ResponseEntity.ok(new OrderDto(ordem.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<OrderDto> attualizar(@PathVariable Long id,@RequestBody @Validated UpdateOrderIn updateOrderIn ){
		Optional<Orders> ordem =  orderRepository.findById(id);
		if (ordem.isPresent()) {
			Orders ordemA = updateOrderIn.atualizar(id, orderRepository);
			return ResponseEntity.ok(new OrderDto(ordemA));
		}
		return ResponseEntity.notFound().build();
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Orders> ordem =  orderRepository.findById(id);
		if (ordem.isPresent()) {
			orderRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	

}
