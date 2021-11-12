package com.br.fabio.consumo.kafka.consumo_kafka.consumerConfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.br.fabio.consumo.kafka.consumo_kafka.dto.OrdersDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Configuration
public class kafkaConsumerConfig {
	
	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapAddress;
	
	@Value("${spring.kafka.group-id}")
	private String groupId;
	
	@Bean
	public NewTopic createTopic() {
		return new NewTopic(groupId, 3,(short) 1);
	}
	
	@Bean
	public ConsumerFactory<String, OrdersDTO> orderConsumerFactory(){
		
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserialize.class);

		
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(OrdersDTO.class, false));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, OrdersDTO> orderKafkaListenerContainerFactory(){
		
		ConcurrentKafkaListenerContainerFactory<String,OrdersDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(orderConsumerFactory());
		return factory;
	}

}
