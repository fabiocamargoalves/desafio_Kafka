package com.br.fabio.consumo.kafka.consumo_kafka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.fabio.consumo.kafka.consumo_kafka.modelo.Orders;

public interface OrdemRepository extends JpaRepository<Orders, Long>{

	List<Orders> findByName(String nameProduto);

}
