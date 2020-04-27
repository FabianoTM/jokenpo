package br.com.ftm.jokenpo.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ftm.jokenpo.business.PartidasService;
import br.com.ftm.jokenpo.domain.Partida;
import br.com.ftm.jokenpo.domain.PartidaResponse;
import br.com.ftm.jokenpo.domain.ResultadoPartida;
import br.com.ftm.jokenpo.enums.ApostasEnum;
import br.com.ftm.jokenpo.exception.JogadaException;

@RestController
@RequestMapping("/jokenpo/partidas")
public class PartidasController {

	@Autowired
	PartidasService partidasService;

	@PostMapping(value = "/iniciar")
	public ResponseEntity<PartidaResponse> iniciarPartida() throws IOException {
		Integer id = partidasService.iniciar();
		return new ResponseEntity<>(
				new PartidaResponse(id, 200, "Partida iniciada com sucesso. O id da partida é: " + id), HttpStatus.OK);
	}

	@PostMapping(value = "/registar")
	public ResponseEntity<PartidaResponse> registrarJogada(Integer idPartida, String idJogador, ApostasEnum aposta) {
		try {
			validarIdPartida(idPartida);
			validarIdJogador(idJogador);
			validarAposta(aposta);
			partidasService.registrar(idPartida, idJogador, aposta);
			return new ResponseEntity<>(new PartidaResponse(idPartida, 200, "Jogada registrada com sucesso."),
					HttpStatus.OK);
		} catch (JogadaException e) {
			return new ResponseEntity<>(new PartidaResponse(idPartida, 500, e.getMessage()), HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/remover")
	public ResponseEntity<PartidaResponse> removerJogada(Integer idPartida, String idJogador) {
		try {
			validarIdPartida(idPartida);
			validarIdJogador(idJogador);
			partidasService.remover(idPartida, idJogador);
			return new ResponseEntity<>(new PartidaResponse(idPartida, 200, "Jogada removida com sucesso."),
					HttpStatus.OK);
		} catch (JogadaException e) {
			return new ResponseEntity<>(new PartidaResponse(idPartida, 500, e.getMessage()), HttpStatus.OK);
		}
	}

	@PutMapping(value = "/encerrar")
	public ResponseEntity<PartidaResponse> encerarPartida(Integer idPartida) {
		try {
			validarIdPartida(idPartida);
			partidasService.encerrar(idPartida);
			return new ResponseEntity<>(new PartidaResponse(idPartida, 200, "Partida encerrada com sucesso."),
					HttpStatus.OK);
		} catch (JogadaException e) {
			return new ResponseEntity<>(new PartidaResponse(idPartida, 500, e.getMessage()), HttpStatus.OK);
		}
	}

	@GetMapping(value = "/resultado")
	public ResponseEntity<?> resultado(Integer idPartida) {
		try {
			validarIdPartida(idPartida);
			ResultadoPartida resultado = partidasService.resultado(idPartida);
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (JogadaException e) {
			return new ResponseEntity<>(new PartidaResponse(idPartida, 500, e.getMessage()), HttpStatus.OK);
		}
	}

	@GetMapping(value = "/listar")
	public ResponseEntity<List<Partida>> listar() {
		List<Partida> partidas = partidasService.listar();
		return new ResponseEntity<>(partidas, HttpStatus.OK);
	}

	private void validarIdPartida(Integer idPartida) throws JogadaException {
		if (idPartida == null) {
			throw new JogadaException("É obrigatório informar o numero da partida.");
		}
	}

	private void validarIdJogador(String idJogador) throws JogadaException {
		if (StringUtils.isBlank(idJogador)) {
			throw new JogadaException("É obrigatório informar o ID do jogador.");
		}
	}

	private void validarAposta(ApostasEnum aposta) throws JogadaException {
		if (aposta == null) {
			throw new JogadaException("É obrigatório informar o ID da aposta.");
		}
	}
}