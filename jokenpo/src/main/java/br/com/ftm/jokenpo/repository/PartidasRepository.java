package br.com.ftm.jokenpo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.ftm.jokenpo.domain.Jogada;
import br.com.ftm.jokenpo.domain.Jogador;
import br.com.ftm.jokenpo.domain.Partida;
import br.com.ftm.jokenpo.exception.JogadaException;

@Repository
public class PartidasRepository {

	private Integer id = 0;
	private Map<Integer, Partida> partidas = new HashMap<Integer, Partida>();

	public Integer iniciar() {
		this.id++;
		partidas.put(id, new Partida(id));
		return id;
	}

	public void registrarJogada(Integer idPartida, Jogada jogada) throws JogadaException {
		partidas.get(idPartida).registrarJogada(jogada);
	}

	public void encerrar(Integer idPartida) throws JogadaException {
		Partida partida = partidas.get(idPartida);
		if (partida == null) {
			throw new JogadaException("ID da partida não localizado.");
		}
		if (partida.isAtiva()) {
			partida.encerrar();
		} else {
			throw new JogadaException("Essa partida já esta encerrada.");
		}

	}

	public List<Partida> listar() {
		List<Partida> lPartidas = new ArrayList<Partida>();
		this.partidas.values().stream().forEach(partida -> lPartidas.add(partida));
		return lPartidas;
	}

	public boolean remover(Integer idPartida, Jogador jogador) throws JogadaException {
		this.partidas.get(idPartida).removerJogada(jogador);
		return true;
	}

}
