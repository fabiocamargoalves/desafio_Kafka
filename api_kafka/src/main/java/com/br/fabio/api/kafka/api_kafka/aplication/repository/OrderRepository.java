package com.br.fabio.api.kafka.api_kafka.aplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.br.fabio.api.kafka.api_kafka.aplication.out.Orders;



public interface OrderRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders>{

	List<Orders> findByName(String name);
	
	default List<Orders> findFilters(String q, double minTotal, double maxTotal) {
	    return findAll(FilterOrderSpecification.findFilters( q, minTotal,  maxTotal));
	  }

}
