package br.com.ftm.jokenpo.domain;

public class JogadorResponse {

	private Integer codRetorno;
	private String mensagem;

	public JogadorResponse(Integer codRetorno, String mensagem) {
		this.codRetorno = codRetorno;
		this.mensagem = mensagem;
	}

	public Integer getCodRetorno() {
		return codRetorno;
	}

	public String getMensagem() {
		return mensagem;
	}

}
