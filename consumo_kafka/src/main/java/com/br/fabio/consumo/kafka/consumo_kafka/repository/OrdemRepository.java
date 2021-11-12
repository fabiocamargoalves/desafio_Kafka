package com.br.fabio.consumo.kafka.consumo_kafka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.fabio.consumo.kafka.consumo_kafka.modelo.Ordem;

public interface OrdemRepository extends JpaRepository<Ordem, Long>{

	List<Ordem> findByName(String nameProduto);

}
