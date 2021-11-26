package com.br.fabio.api.kafka.api_kafka.aplication.validacao;

public class ErrorDto {
	
	private String campo;
	private String erro;
	
	
	public ErrorDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}


	public String getCampo() {
		return campo;
	}


	public void setCampo(String campo) {
		this.campo = campo;
	}


	public String getErro() {
		return erro;
	}


	public void setErro(String erro) {
		this.erro = erro;
	}
	
	
}
