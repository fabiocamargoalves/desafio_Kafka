package com.br.fabio.consumo.kafka.consumo_kafka.consumer;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.fabio.consumo.kafka.consumo_kafka.dto.OrdersDTO;
import com.br.fabio.consumo.kafka.consumo_kafka.form.AtualizaOrdemForm;
import com.br.fabio.consumo.kafka.consumo_kafka.modelo.Ordem;
import com.br.fabio.consumo.kafka.consumo_kafka.repository.OrdemRepository;

@RestController
@Component
public class OrdersConsumer {

	private static final Logger log = LoggerFactory.getLogger(OrdersConsumer.class);
	
	@Value("${topic.name}")
	private String topic;
	
	@Value("${spring.kafka.group-id}")
	private String groupId;
	
	@Autowired
	private OrdemRepository ordemRepository;
	
	AtualizaOrdemForm ordemForm;
	
	@Transactional
	@KafkaListener(topics="${topic.name}", groupId = "${spring.kafka.group-id}", containerFactory = "orderKafkaListenerContainerFactory")
	public void listenTopicOrder(ConsumerRecord<String, OrdersDTO> record) {
		log.info("Received Message Partition: " + record.partition());
		log.info("Received Message: " + record.value());
		
		OrdersDTO od = record.value();
		String name = od.getName();
		
	
		List<Ordem> list = ordemRepository.findByName(name);
		
		List<OrdersDTO> odt = OrdersDTO.converter(list);
		
		for (OrdersDTO ordem : odt) {
			AtualizaOrdemForm af = new AtualizaOrdemForm();
			af.setName(ordem.getName());
			af.setTotal(ordem.getTotal());
			af.setStatus(ordem.getStatus());
			atualizar(ordem.getId(),af);
		}
	}
	
	public void atualizar(Long id,@RequestBody @Validated AtualizaOrdemForm ordemForm ){
		Optional<Ordem> ordem =  ordemRepository.findById(id);
		if (ordem.isPresent()) {
			ordemForm.atualizar(id, ordemRepository);
			System.out.println("++++++++++++++++++++++ SUCESSO ++++++++++++++++++++++++");
		}
	
	}
	
	
	
	
}
