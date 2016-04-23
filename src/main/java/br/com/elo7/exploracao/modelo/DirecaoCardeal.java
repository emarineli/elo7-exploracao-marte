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

	NORTE("N"), SUL("S"), LESTE("L"), OESTE("O");

	private String representacaoString;

	public static DirecaoCardeal DIRECAO_PADRAO = DirecaoCardeal.NORTE;

	private DirecaoCardeal(String representacaoString) {
		this.representacaoString = representacaoString;
	}

}
