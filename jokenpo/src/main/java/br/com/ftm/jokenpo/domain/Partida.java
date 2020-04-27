package br.com.ftm.jokenpo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.ftm.jokenpo.exception.JogadaException;

public class Partida {

	private Integer id;
	private boolean ativa;
	private List<Jogada> jogadas;

	public Partida(Integer id) {
		this.id = id;
		this.ativa = true;
		this.jogadas = new ArrayList<Jogada>();
	}

	public Integer getId() {
		return id;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public List<Jogada> getJogadas() {
		return jogadas;
	}

	public void registrarJogada(Jogada jogada) throws JogadaException {
		if (jogadas.stream().filter(j -> j.getJogador().equals(jogada.getJogador())).findFirst().isPresent()) {
			throw new JogadaException("Você já registrou sua jogada para essa partida.");
		}
		if (ativa) {
			this.jogadas.add(jogada);
		} else {
			throw new JogadaException("Essa partida já esta encerrada.");
		}
	}

	public void removerJogada(Jogador jogador) throws JogadaException {
		if (!jogadas.stream().filter(j -> j.getJogador().equals(jogador)).findFirst().isPresent()) {
			throw new JogadaException("Não foi localizada nenhuma jogada para esse jogador nessa partida.");
		}
		if (ativa) {
			this.jogadas = jogadas.stream().filter(j -> !j.getJogador().equals(jogador)).collect(Collectors.toList());
		} else {
			throw new JogadaException("Essa partida já esta encerrada.");
		}
	}

	public void encerrar() {
		this.ativa = false;
	}
}
