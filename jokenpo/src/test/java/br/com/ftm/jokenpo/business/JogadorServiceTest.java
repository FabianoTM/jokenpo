package br.com.ftm.jokenpo.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.ftm.jokenpo.domain.Jogador;
import br.com.ftm.jokenpo.exception.JogadorException;
import br.com.ftm.jokenpo.repository.JogadorRepository;

@RunWith(SpringRunner.class)
public class JogadorServiceTest {

	JogadorService jogadorService;

	@Before
	public void setUp() {
		JogadorRepository jogadorRepository = new JogadorRepository();
		jogadorService = new JogadorService();
		ReflectionTestUtils.setField(jogadorService, "jogadorRepository", jogadorRepository);
	}

	@Test
	public void deveIncluirJogadorComSucesso() throws JogadorException {
		Jogador jogador = new Jogador("JUnit_01");
		jogadorService.registrar(jogador);
		assertTrue(jogadorService.listar().stream().filter(j -> j.equals(jogador)).findAny().isPresent());
	}

	@Test
	public void deveRemoverJogadorComSucesso() throws JogadorException {
		Jogador jogador1 = new Jogador("JUnit_01");
		Jogador jogador2 = new Jogador("JUnit_02");
		jogadorService.registrar(jogador1);
		jogadorService.registrar(jogador2);
		jogadorService.remover(jogador1);
		assertFalse(jogadorService.listar().stream().filter(j -> j.equals(jogador1)).findAny().isPresent());
	}

	@Test
	public void deveValidarJogadorComSucesso() throws JogadorException {
		Jogador jogador1 = new Jogador("JUnit_01");
		jogadorService.registrar(jogador1);
		Jogador validarJogador = jogadorService.validarJogador("JUnit_01");
		assertTrue(validarJogador.equals(jogador1));
	}

	@Test(expected = JogadorException.class)
	public void deveFalharAoValidarJogadorInexistente() throws JogadorException {
		Jogador jogador1 = new Jogador("JUnit_01");
		jogadorService.registrar(jogador1);
		jogadorService.validarJogador("JUnit_11");
	}
}
