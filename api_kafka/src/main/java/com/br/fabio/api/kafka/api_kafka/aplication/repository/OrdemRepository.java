package com.br.fabio.api.kafka.api_kafka.aplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.fabio.api.kafka.api_kafka.aplication.modelo.Ordem;



public interface OrdemRepository extends JpaRepository<Ordem, Long>{

	List<Ordem> findByName(String nameProduto);

}
