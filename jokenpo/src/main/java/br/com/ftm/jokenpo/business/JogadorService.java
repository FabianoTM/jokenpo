package br.com.ftm.jokenpo.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ftm.jokenpo.domain.Jogador;
import br.com.ftm.jokenpo.exception.JogadorException;
import br.com.ftm.jokenpo.repository.JogadorRepository;

@Service
public class JogadorService {

	@Autowired
	JogadorRepository jogadorRepository;

	public void registrar(Jogador jogador) throws JogadorException {

		jogadorRepository.adicionarJogador(jogador);

	}

	public void remover(Jogador jogador) throws JogadorException {
		jogadorRepository.remover(jogador);
	}

	public List<Jogador> listar() {

		return jogadorRepository.listarJogadores();

	}

}
