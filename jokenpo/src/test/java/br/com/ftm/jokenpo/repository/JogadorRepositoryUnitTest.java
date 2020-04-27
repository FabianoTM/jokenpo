package br.com.ftm.jokenpo.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ftm.jokenpo.domain.Jogador;
import br.com.ftm.jokenpo.exception.JogadorException;

@RunWith(SpringRunner.class)
public class JogadorRepositoryUnitTest {

	JogadorRepository jogadorRepository;

	@Before
	public void setUp() {
		jogadorRepository = new JogadorRepository();
	}

	@Test
	public void deveAdicionarJogadorComSucesso() throws JogadorException {
		Jogador jogador = new Jogador("Junit");
		assertTrue(jogadorRepository.adicionarJogador(jogador));
	}

	@Test(expected = JogadorException.class)
	public void deveFalharAoAdicionarJogadorJaRegistrado() throws JogadorException {
		Jogador jogador1 = new Jogador("Junit");
		Jogador jogador2 = new Jogador("Junit");
		jogadorRepository.adicionarJogador(jogador1);
		jogadorRepository.adicionarJogador(jogador2);
	}

	@Test
	public void deveRemoverJogadorComSucesso() throws JogadorException {
		Jogador jogador1 = new Jogador("Junit1");
		Jogador jogador2 = new Jogador("Junit2");
		jogadorRepository.adicionarJogador(jogador1);
		jogadorRepository.adicionarJogador(jogador2);
		assertTrue(jogadorRepository.remover(jogador1));
	}

	@Test(expected = JogadorException.class)
	public void deveFalharAoRemoverJogadorNaoRegistrado() throws JogadorException {
		Jogador jogador1 = new Jogador("Junit1");
		Jogador jogador2 = new Jogador("Junit2");
		jogadorRepository.adicionarJogador(jogador1);
		jogadorRepository.remover(jogador2);
	}

	@Test
	public void deveListarJogadoresComSucesso() throws JogadorException {
		Jogador jogador1 = new Jogador("Junit1");
		Jogador jogador2 = new Jogador("Junit2");
		jogadorRepository.adicionarJogador(jogador1);
		jogadorRepository.adicionarJogador(jogador2);
		assertTrue(jogadorRepository.listarJogadores().size() == 2);
	}

}
