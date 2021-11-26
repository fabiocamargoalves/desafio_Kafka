package com.br.fabio.api.kafka.api_kafka.aplication.dto;

 

import org.springframework.lang.Nullable;

import com.br.fabio.api.kafka.api_kafka.aplication.validacao.ValidatedParametersException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

 
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FiltroDto { 

	 
	private static final String INVALID_NUMBER_FOR_PRICE = "Invalid Number for total";


	@Nullable
	@JsonProperty("q")
	private String q;

	 
	@Nullable
	@JsonProperty("min_total")
	private String min_total;

	 
	@Nullable
	@JsonProperty("max_total")
	private String max_total;

	private double minTotaldb = -1.0;
	private double maxTotaldb = -1.0;

 

	public boolean isValid() throws ValidatedParametersException {

		try {

			if (null != max_total) {
				this.maxTotaldb = Double.parseDouble(max_total);
				
			}

			if (null != min_total) {
				this.minTotaldb = Double.parseDouble(min_total);
			}
		} catch (NumberFormatException e) {
			throw new ValidatedParametersException( INVALID_NUMBER_FOR_PRICE, null, max_total, null, e); 
		}

		return true;
	}

}
