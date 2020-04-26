package br.com.ftm.jokenpo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ftm.jokenpo.business.JogadorService;
import br.com.ftm.jokenpo.domain.Jogador;
import br.com.ftm.jokenpo.domain.JogadorResponse;
import br.com.ftm.jokenpo.exception.JogadorException;

@RestController
@RequestMapping("/jokenpo/jogadores")
public class JogadorController {

	@Autowired
	JogadorService jogadorService;

	@PostMapping(value = "/registrar")
	public ResponseEntity<JogadorResponse> registrar(@RequestBody Jogador jogador) {
		try {
			jogadorService.registrar(jogador);
			return new ResponseEntity<>(new JogadorResponse(200, "Jogador registrado com sucesso."), HttpStatus.OK);
		} catch (JogadorException e) {
			return new ResponseEntity<>(new JogadorResponse(500, e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/remover")
	public ResponseEntity<JogadorResponse> remover(@RequestBody Jogador jogador) {
		try {
			jogadorService.remover(jogador);
			return new ResponseEntity<>(new JogadorResponse(200, "Jogador removido com sucesso."), HttpStatus.OK);
		} catch (JogadorException e) {
			return new ResponseEntity<>(new JogadorResponse(500, e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/listar")
	public ResponseEntity<List<Jogador>> listar() {
		return new ResponseEntity<>(jogadorService.listar(), HttpStatus.OK);
	}

}