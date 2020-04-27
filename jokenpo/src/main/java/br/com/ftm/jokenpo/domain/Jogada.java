package br.com.ftm.jokenpo.domain;

import br.com.ftm.jokenpo.enums.ApostasEnum;

public class Jogada {

	private Jogador jogador;
	private ApostasEnum aposta;

	public Jogada(Jogador jogador, ApostasEnum aposta) {
		this.jogador = jogador;
		this.aposta = aposta;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public ApostasEnum getAposta() {
		return aposta;
	}

}
