package br.com.elo7.exploracao;

import br.com.elo7.exploracao.modelo.Coordenada;

/**
 * Representa qualquer veículo de exploração.
 * 
 * @author emarineli
 *
 */
public interface VeiculoExploracao {

	/**
	 * Realiza a movimentação de um veículo de exploração.
	 */
	void movimentar(Coordenada[] coordenadas);

}
