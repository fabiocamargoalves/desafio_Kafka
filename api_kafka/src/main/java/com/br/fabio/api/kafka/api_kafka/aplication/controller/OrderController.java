package com.br.fabio.api.kafka.api_kafka.aplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.fabio.api.kafka.api_kafka.aplication.dto.FiltroDto;
import com.br.fabio.api.kafka.api_kafka.aplication.dto.OrderDto;
import com.br.fabio.api.kafka.api_kafka.aplication.in.OrderIn;
import com.br.fabio.api.kafka.api_kafka.aplication.in.UpdateOrderIn;
import com.br.fabio.api.kafka.api_kafka.aplication.services.OrderService;
import com.br.fabio.api.kafka.api_kafka.aplication.validacao.ValidatedParametersException;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private static final String UTF_8 = "utf-8";
	private static final String DATA_ENCODING = "DataEncoding";
	
	@Autowired
	private OrderService orderService;
	
	
	/**
	 * List all product in catalog
	 * 
	 * @return
	 */
	@GetMapping
	public List<OrderDto> list(String name){
		return orderService.lista(name);
	}
	/**
	 * 
	 * 
	 * 
	 * @param orderIn
	 * @param uriBuilder
	 * @return
	 */
	@PostMapping
	public ResponseEntity<OrderDto> create(@RequestBody @Validated OrderIn orderIn, UriComponentsBuilder uriBuilder) {
		return orderService.cadastrar(orderIn, uriBuilder);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/{id}")
	public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
		return orderService.detalhar(id);
	}
	
	/**
	 * 
	 */
	@PutMapping("/{id}")
	public ResponseEntity<OrderDto> update(@PathVariable Long id,@RequestBody @Validated UpdateOrderIn updateOrderIn ){
		return orderService.attualizar(id, updateOrderIn);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return orderService.remover(id);
	}
	
	/**
	 * 
	 * @param dto
	 * @return
	 * @throws ValidatedParametersException
	 */
	@GetMapping(value = "/search")
	public ResponseEntity<List<OrderDto>> search(@Validated FiltroDto dto) throws ValidatedParametersException {
		dto.isValid();
		return ResponseEntity.ok().header(DATA_ENCODING, UTF_8).body(orderService.findFilters(dto.getQ(),dto.getMinTotaldb(),dto.getMaxTotaldb()));
	}                                                        

}
