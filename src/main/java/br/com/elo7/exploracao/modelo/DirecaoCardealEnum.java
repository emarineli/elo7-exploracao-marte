package br.com.elo7.exploracao.modelo;

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
	NORTE("N", "O", "L"), SUL("S", "L", "O"), LESTE("L", "N", "S"), OESTE("O", "S", "N");

	private String representacaoString;
	private String proximaDirecaoEsquerda;
	private String proximaDirecaoDireita;

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
	 */
	private DirecaoCardealEnum(String representacaoString, String proximaDirecaoEsquerda, String proximaDirecaoDireita) {
		this.representacaoString = representacaoString;
		this.proximaDirecaoDireita = proximaDirecaoDireita;
		this.proximaDirecaoEsquerda = proximaDirecaoEsquerda;
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

	/**
	 * Obtém uma direção por sua representação em string.
	 * 
	 * @param representacaoString
	 *            representação em string da direção cardeal.
	 * @return direção caso tenha sido encontrada.
	 */
	public DirecaoCardealEnum obterDirecaoPorRepresentacao(String representacaoString) {

		for (DirecaoCardealEnum direcao : values()) {
			if (direcao.obterRepresentacaoString().equals(representacaoString)) {
				return direcao;
			}
		}

		return null;
	}

	/**
	 * Obtém qual é a próxima direção cardeal à esquerda da direção atual.
	 * Exemplo: Caso a direção atual for NORTE, a próxima direção à esquerda é
	 * OESTE.
	 * 
	 * @return próxima direção cardeal à esquerda da direção atual.
	 */
	public DirecaoCardealEnum obterProximaDirecaoEsquerda() {
		return obterDirecaoPorRepresentacao(this.proximaDirecaoEsquerda);
	}

	/**
	 * Obtém qual é a próxima direção cardeal à direita da direção atual.
	 * Exemplo: Caso a direção atual for NORTE, a próxima direção à direita é
	 * LESTE.
	 * 
	 * @return próxima direção cardeal à direita da direção atual.
	 */
	public DirecaoCardealEnum obterProximaDirecaoDireita() {
		return obterDirecaoPorRepresentacao(this.proximaDirecaoDireita);
	}

}
