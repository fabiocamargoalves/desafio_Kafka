package com.br.fabio.api.kafka.api_kafka.aplication.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.br.fabio.api.kafka.api_kafka.aplication.out.Orders;

 
public final class FilterOrderSpecification implements Serializable {


	private static final long serialVersionUID = 1L;

	private  FilterOrderSpecification() {
 	}

	public static Specification<Orders> findFilters(String q, double minTotal, double maxTotal) {
		return new Specification<Orders>() {

			private static final long serialVersionUID = 1L;
			transient  Predicate predicateLike = null;
			transient Predicate predicateMinTotal = null;
			transient  Predicate predicateMaxTotal = null;


			@Override
			public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();

				if (null != q) {
					predicateLike = criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),  criteriaBuilder.lower(criteriaBuilder.literal("%" + q + "%"))),
							criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), criteriaBuilder.lower(criteriaBuilder.literal("%" + q + "%"))));
					predicates.add(predicateLike);
				}

				if (minTotal >= 0) {
					predicateMinTotal = criteriaBuilder.greaterThanOrEqualTo(root.get("total"), minTotal);
					predicates.add(predicateMinTotal);
				}

				if (maxTotal >= 0) {
					predicateMaxTotal = criteriaBuilder.lessThanOrEqualTo(root.get("total"), maxTotal);
					predicates.add(predicateMaxTotal);
				}

				return query.where(predicates.toArray(Predicate[]::new)).getRestriction();
			}

			 
		};

	}

}
