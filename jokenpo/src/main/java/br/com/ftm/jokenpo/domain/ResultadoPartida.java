package br.com.ftm.jokenpo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.ftm.jokenpo.enums.ApostasEnum;

public class ResultadoPartida {
	private List<Jogada> jogadas;
	private List<Jogada> jogadaVencedora;
	private List<Jogador> vencedores;

	public ResultadoPartida(List<Jogada> jogadas) {
		this.jogadas = jogadas;
		this.jogadaVencedora = new ArrayList<Jogada>();
		jogadas.stream().forEach(j -> jogadaVencedora.add(new Jogada(j.getJogador(), j.getAposta())));
	}

	public List<Jogada> getJogadaVencedora() {
		return jogadaVencedora;
	}

	public List<Jogador> getVencedores() {
		return vencedores;
	}

	public ResultadoPartida obterVencedor() {
		obterVencedorParcialTesoura();
		obterVencedorParcialPapel();
		obterVencedorParcialPedra();
		obterVencedorParcialLagarto();
		obterVencedorParcialSpock();
		this.vencedores = new ArrayList<Jogador>();
		jogadaVencedora.stream().forEach(j -> vencedores.add(new Jogador(j.getJogador().getId())));
		System.out.println(getMensagem());
		return this;
	}

	public String getMensagem() {
		if (jogadaVencedora.size() == 1) {
			return "O vencedor dessa partida foi o jogador: " + jogadaVencedora.get(0).getJogador().getId();
		}
		if (jogadaVencedora.size() == jogadas.size()) {
			return "Não hoouve vencedor para essa partida";
		} else {
			return ("Houve um empate com " + jogadaVencedora.size() + "jogadores. Lista dos vencedores: " + String
					.join(",", jogadaVencedora.stream().map(j -> j.getJogador().getId()).collect(Collectors.toList())));
		}
	}

	private void obterVencedorParcialTesoura() {
		if (jogadaVencedora.stream().filter(j -> j.getAposta().equals(ApostasEnum.TESOURA)).findFirst().isPresent()) {
			List<Jogada> parcial = jogadaVencedora.stream().filter(j -> (j.getAposta() != ApostasEnum.LAGARTO))
					.filter(j -> (j.getAposta() != ApostasEnum.PAPEL)).collect(Collectors.toList());
			if (!parcial.isEmpty()) {
				this.jogadaVencedora = parcial;
			}
		}
	}

	private void obterVencedorParcialPapel() {
		if (jogadaVencedora.stream().filter(j -> j.getAposta().equals(ApostasEnum.PAPEL)).findFirst().isPresent()) {
			List<Jogada> parcial = jogadaVencedora.stream().filter(j -> (j.getAposta() != ApostasEnum.SPOCK))
					.filter(j -> (j.getAposta() != ApostasEnum.PEDRA)).collect(Collectors.toList());
			if (!parcial.isEmpty()) {
				this.jogadaVencedora = parcial;
			}
		}
	}

	private void obterVencedorParcialPedra() {

		if (jogadaVencedora.stream().filter(j -> j.getAposta().equals(ApostasEnum.PEDRA)).findFirst().isPresent()) {
			List<Jogada> parcial = jogadaVencedora.stream().filter(j -> (j.getAposta() != ApostasEnum.LAGARTO))
					.filter(j -> (j.getAposta() != ApostasEnum.TESOURA)).collect(Collectors.toList());
			if (!parcial.isEmpty()) {
				this.jogadaVencedora = parcial;
			}
		}
	}

	private void obterVencedorParcialLagarto() {

		if (jogadaVencedora.stream().filter(j -> j.getAposta().equals(ApostasEnum.LAGARTO)).findFirst().isPresent()) {
			List<Jogada> parcial = jogadaVencedora.stream().filter(j -> (j.getAposta() != ApostasEnum.PAPEL))
					.collect(Collectors.toList());
			if (!parcial.isEmpty()) {
				this.jogadaVencedora = parcial;
			}
		}
	}

	private void obterVencedorParcialSpock() {

		if (jogadaVencedora.stream().filter(j -> j.getAposta().equals(ApostasEnum.SPOCK)).findFirst().isPresent()) {
			List<Jogada> parcial = jogadaVencedora.stream().filter(j -> (j.getAposta() != ApostasEnum.TESOURA))
					.filter(j -> (j.getAposta() != ApostasEnum.PEDRA)).collect(Collectors.toList());
			if (!parcial.isEmpty()) {
				this.jogadaVencedora = parcial;
			}
		}
	}

}
