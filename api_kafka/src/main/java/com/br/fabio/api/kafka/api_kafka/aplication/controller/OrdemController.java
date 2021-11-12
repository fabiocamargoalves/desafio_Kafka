package com.br.fabio.api.kafka.api_kafka.aplication.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

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

import com.br.fabio.api.kafka.api_kafka.aplication.dto.OrdemDto;
import com.br.fabio.api.kafka.api_kafka.aplication.form.AtualizaOrdemForm;
import com.br.fabio.api.kafka.api_kafka.aplication.form.OrdemForm;
import com.br.fabio.api.kafka.api_kafka.aplication.modelo.Ordem;
import com.br.fabio.api.kafka.api_kafka.aplication.produtor.OrdersProdutor;
import com.br.fabio.api.kafka.api_kafka.aplication.repository.OrdemRepository;




@RestController
@RequestMapping("/listaOrdens")
public class OrdemController {
	
	@Autowired
	private OrdemRepository ordemRepository;
	
	@Autowired
	private OrdersProdutor ordersProdutor;
	
	
	@GetMapping
	public List<OrdemDto> lista(String nameProduto){
		
		if (nameProduto == null) {
			List<Ordem> ordens = ordemRepository.findAll();
			return OrdemDto.converter(ordens);
		}else {
			List<Ordem> ordens = ordemRepository.findByName(nameProduto);
			return OrdemDto.converter(ordens);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<OrdemDto> cadastrar(@RequestBody @Validated OrdemForm ordemForm, UriComponentsBuilder uriBuilder) {
		Ordem ordem = ordemForm.converter();
		ordemRepository.save(ordem);
		URI uri = uriBuilder.path("/listaOrdens/{id}").buildAndExpand(ordem.getId()).toUri();
		
		ordersProdutor.send(ordemForm);
		
		
		return ResponseEntity.created(uri).body(new OrdemDto(ordem));
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrdemDto> detalhar(@PathVariable Long id) {
		Optional<Ordem> ordem =  ordemRepository.findById(id);
		if (ordem.isPresent()) {
			return ResponseEntity.ok(new OrdemDto(ordem.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<OrdemDto> attualizar(@PathVariable Long id,@RequestBody @Validated AtualizaOrdemForm ordemForm ){
		Optional<Ordem> ordem =  ordemRepository.findById(id);
		if (ordem.isPresent()) {
			Ordem ordemA = ordemForm.atualizar(id, ordemRepository);
			return ResponseEntity.ok(new OrdemDto(ordemA));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Ordem> ordem =  ordemRepository.findById(id);
		if (ordem.isPresent()) {
			ordemRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
