package br.com.ftm.jokenpo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PartidaResponse {

	private Integer idPartida;
	private Integer codRetorno;
	private String mensagem;

	public PartidaResponse(Integer idPartida, Integer codRetorno, String mensagem) {
		super();
		this.idPartida = idPartida;
		this.codRetorno = codRetorno;
		this.mensagem = mensagem;
	}

	public Integer getIdPartida() {
		return idPartida;
	}

	public Integer getCodRetorno() {
		return codRetorno;
	}

	public String getMensagem() {
		return mensagem;
	}
}
