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
public enum DirecaoCardeal {

	/*
	 * Os enumeradores já são definidos com suas orientações a 90 graus à
	 * esquerda e direita
	 */
	NORTE("N", "O", "L"), SUL("S", "L", "O"), LESTE("L", "N", "S"), OESTE("O", "S", "N");

	private String representacaoString;
	private String proximaDirecaoEsquerda;
	private String proximaDirecaoDireita;

	public static DirecaoCardeal DIRECAO_PADRAO = DirecaoCardeal.NORTE;

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
	private DirecaoCardeal(String representacaoString, String proximaDirecaoEsquerda, String proximaDirecaoDireita) {
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
	 * @return diração caso tenha sido encontrada.
	 */
	public DirecaoCardeal obterDirecaoPorRepresentacao(String representacaoString) {

		for (DirecaoCardeal direcao : values()) {
			if (direcao.obterRepresentacaoString().equals(representacaoString)) {
				return direcao;
			}
		}

		return null;
	}

	public DirecaoCardeal obterProximaDirecaoEsquerda() {
		return obterDirecaoPorRepresentacao(this.proximaDirecaoEsquerda);
	}

	public DirecaoCardeal obterProximaDirecaoDireita() {
		return obterDirecaoPorRepresentacao(this.proximaDirecaoDireita);
	}

}
