package br.com.ftm.jokenpo.business;

import java.util.List;
import java.util.Optional;

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

	public Jogador validarJogador(String idJogador) throws JogadorException {
		Optional<Jogador> jogador = jogadorRepository.listarJogadores().stream().filter(j -> j.getId().equals(idJogador)).findFirst();
		
		if(jogador.isPresent()) {
			return jogador.get();
		}else {
			throw new JogadorException("Usuário não localizado!");
		}
	}

}
