package com.br.fabio.api.kafka.api_kafka.aplication.produtor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import com.br.fabio.api.kafka.api_kafka.aplication.form.OrdemForm;


@Service
public class OrdersProdutor {
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersProdutor.class);
	private final String topic;
	private final KafkaTemplate<String, OrdemForm> kafkaTemplate;
	
  
  public OrdersProdutor(@Value("${topic.name}") String topic, KafkaTemplate<String, OrdemForm> kafkaTemplate) {
	  this.topic = topic;
	  this.kafkaTemplate = kafkaTemplate;
  }

  public void send(OrdemForm ordernsDTO) {
	  System.out.println("============= INICIO Template Topic =========== "+topic);
	  kafkaTemplate.send(topic, ordernsDTO).addCallback(
			   sucess -> logger.info("Mensagem Enviada" + sucess.getProducerRecord().value()),
			   failure -> logger.info("Mensagem Falhou" + failure.getMessage()));
	  
	  System.out.println("============= FIM Template ===========");
  }
}
