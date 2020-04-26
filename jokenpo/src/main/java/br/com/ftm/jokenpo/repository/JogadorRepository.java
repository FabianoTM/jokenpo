package br.com.ftm.jokenpo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.ftm.jokenpo.domain.Jogador;
import br.com.ftm.jokenpo.exception.JogadorException;

@Repository
public class JogadorRepository {

	private List<Jogador> jogadores;

	public JogadorRepository() {
		this.jogadores = new ArrayList<Jogador>();
	}

	public List<Jogador> listarJogadores() {
		return jogadores;
	}

	public boolean adicionarJogador(Jogador jogador) throws JogadorException {
		if (jogadores.stream().filter(j -> j.getId().equals(jogador.getId())).findAny().isPresent()) {
			throw new JogadorException("O id de usuário solicitado já está registrado, favor informar outro id.");
		}
		this.jogadores.add(jogador);
		return true;
	}

	public boolean remover(Jogador jogador) throws JogadorException {
		if (!jogadores.stream().filter(j -> j.getId().equals(jogador.getId())).findAny().isPresent()) {
			throw new JogadorException("O id de usuário informado não está registrado!.");
		}
		this.jogadores = jogadores.stream().filter((j -> !j.getId().equals(jogador.getId())))
				.collect(Collectors.toList());
		return true;

	}

}
