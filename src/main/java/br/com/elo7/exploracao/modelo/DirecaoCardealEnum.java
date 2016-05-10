package br.com.elo7.exploracao.modelo;

import java.util.Optional;

import br.com.elo7.exploracao.modelo.state.MovimentacaoLesteState;
import br.com.elo7.exploracao.modelo.state.MovimentacaoNorteState;
import br.com.elo7.exploracao.modelo.state.MovimentacaoOesteState;
import br.com.elo7.exploracao.modelo.state.MovimentacaoState;
import br.com.elo7.exploracao.modelo.state.MovimentacaoSulState;

/**
 * Representa uma direção cardeal da rosa dos ventos.
 * 
 * Uma direção do tipo cardeal só pode apresentar as coordenadas NORTE, SUL,
 * LESTE e OESTE.
 * 
 * @author emarineli
 *
 */
public enum DirecaoCardealEnum {

	/*
	 * Os enumeradores já são definidos com suas orientações a 90 graus à
	 * esquerda e direita
	 */
	NORTE("N", "O", "L", new MovimentacaoNorteState()), SUL("S", "L", "O",
			new MovimentacaoSulState()), LESTE("L", "N", "S",
			new MovimentacaoLesteState()), OESTE("O", "S", "N",
			new MovimentacaoOesteState());

	private String representacaoString;
	private String proximaDirecaoEsquerda;
	private String proximaDirecaoDireita;

	private MovimentacaoState estadoMovimentacao;

	public static DirecaoCardealEnum DIRECAO_PADRAO = DirecaoCardealEnum.NORTE;

	/**
	 * Cria uma direção cardeal
	 * 
	 * @param representacaoString
	 *            Representação em string da orientação.
	 * @param proximaDirecaoEsquerda
	 *            representação em string da próxima direção a 90 graus à
	 *            esquerda.
	 * @param proximaDirecaoDireita
	 *            representação em string da próxima direçção a 90 graus à
	 *            direita.
	 * @param estadoMovimentacao
	 *            estado de movimentação da sonda.
	 */
	private DirecaoCardealEnum(String representacaoString,
			String proximaDirecaoEsquerda, String proximaDirecaoDireita,
			MovimentacaoState estadoMovimentacao) {
		this.representacaoString = representacaoString;
		this.proximaDirecaoDireita = proximaDirecaoDireita;
		this.proximaDirecaoEsquerda = proximaDirecaoEsquerda;

		this.estadoMovimentacao = estadoMovimentacao;
	}

	public String obterRepresentacaoString() {
		return this.representacaoString;
	}

	public String obterRepresentacaoProximaDirecaoEsquerda() {
		return this.proximaDirecaoEsquerda;
	}

	public String obterRepresentacaoProximaDirecaoDireta() {
		return this.proximaDirecaoDireita;
	}

	public MovimentacaoState obterEstadoMovimentacao() {
		return this.estadoMovimentacao;
	}

	/**
	 * Obtém uma direção por sua representação em string.
	 * 
	 * @param representacaoString
	 *            representação em string da direção cardeal.
	 * @return direção caso tenha sido encontrada.
	 */
	public Optional<DirecaoCardealEnum> obterDirecaoPorRepresentacao(
			String representacaoString) {

		for (DirecaoCardealEnum direcao : values()) {
			if (direcao.obterRepresentacaoString().equals(representacaoString)) {
				return Optional.of(direcao);
			}
		}

		return Optional.empty();
	}

	/**
	 * Obtém qual é a próxima direção cardeal à esquerda da direção atual.
	 * Exemplo: Caso a direção atual for NORTE, a próxima direção à esquerda é
	 * OESTE.
	 * 
	 * @return próxima direção cardeal à esquerda da direção atual.
	 */
	public Optional<DirecaoCardealEnum> obterProximaDirecaoEsquerda() {
		return obterDirecaoPorRepresentacao(this.proximaDirecaoEsquerda);
	}

	/**
	 * Obtém qual é a próxima direção cardeal à direita da direção atual.
	 * Exemplo: Caso a direção atual for NORTE, a próxima direção à direita é
	 * LESTE.
	 * 
	 * @return próxima direção cardeal à direita da direção atual.
	 */
	public Optional<DirecaoCardealEnum> obterProximaDirecaoDireita() {
		return obterDirecaoPorRepresentacao(this.proximaDirecaoDireita);
	}

}
