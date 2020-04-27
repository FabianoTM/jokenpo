package br.com.ftm.jokenpo.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ftm.jokenpo.domain.Jogada;
import br.com.ftm.jokenpo.domain.Jogador;
import br.com.ftm.jokenpo.domain.Partida;
import br.com.ftm.jokenpo.domain.ResultadoPartida;
import br.com.ftm.jokenpo.enums.ApostasEnum;
import br.com.ftm.jokenpo.exception.JogadaException;
import br.com.ftm.jokenpo.exception.JogadorException;
import br.com.ftm.jokenpo.repository.PartidasRepository;

@Service
public class PartidasService {

	@Autowired
	PartidasRepository partidaRepository;

	@Autowired
	JogadorService jogadorService;

	public Integer iniciar() {
		return partidaRepository.iniciar();
	}

	public boolean registrar(Integer idPartida, String idJogador, ApostasEnum aposta) throws JogadaException {
		Jogador jogador = null;
		try {
			jogador = jogadorService.validarJogador(idJogador);
		} catch (JogadorException e) {
			throw new JogadaException(
					"O id do jogador ainda não está registrado no sistema. Favor se registrar antes de jogar.");
		}
		Partida partida = validar(idPartida);
		if (partida.isAtiva()) {
			Jogada jogada = new Jogada(jogador, aposta);
			partidaRepository.registrarJogada(idPartida, jogada);
		} else {
			throw new JogadaException("Não foi possivel registrar sua jogada, essa partida já foi encerrada.");
		}
		return true;
	}

	public boolean encerrar(Integer idPartida) throws JogadaException {
		validar(idPartida);
		partidaRepository.encerrar(idPartida);
		return true;
	}

	private Partida validar(Integer idPartida) throws JogadaException {
		List<Partida> partidas = partidaRepository.listar();
		Optional<Partida> partida = partidas.stream().filter(p -> p.getId().equals(idPartida)).findFirst();
		if (partida.isPresent()) {
			return partida.get();
		} else {
			throw new JogadaException(
					"ID da partida não localizado. Provavelmente essa partida não foi iniciada ainda.");
		}
	}

	public List<Partida> listar() {
		return partidaRepository.listar();
	}

	public ResultadoPartida resultado(Integer idPartida) throws JogadaException {
		Partida partida = validar(idPartida);
		if (partida.isAtiva()) {
			throw new JogadaException("A partida ainda está em andamento. É necessário encerrar a partida primeiro.");
		}
		if (partida.getJogadas().size() == 1) {
			throw new JogadaException("Houve apenas um jogador para essa partida.");
		}

		ResultadoPartida resultado = new ResultadoPartida(partida.getJogadas());
		resultado.obterVencedor();
		return resultado;
	}

	public void remover(Integer idPartida, String idJogador) throws JogadaException {
		Partida partida = validar(idPartida);
		Jogador jogador = null;
		try {
			jogador = jogadorService.validarJogador(idJogador);
		} catch (JogadorException e) {
			throw new JogadaException(
					"O id do jogador ainda não está registrado no sistema. Favor se registrar antes de jogar.");
		}
		if (partida.isAtiva()) {
			throw new JogadaException("Não foi possível remover a jogada, pois a partida já foi encerrada.");
		}
		partidaRepository.remover(idPartida, jogador);
	}

}
