package br.com.elo7.exploracao.modelo;

/**
 * Represenda uma coordenada de movimentação.
 * 
 * @author emarineli
 *
 */
public enum Coordenada {

	ESQUERDA("E"), DIREITA("D"), MOVER("M");

	private String representacaoString;

	/**
	 * Cria uma coordenada indicando sua representação em string.
	 * 
	 * @param representacaoString
	 *            representação em strin.
	 */
	private Coordenada(String representacaoString) {
		this.representacaoString = representacaoString;
	}

}
