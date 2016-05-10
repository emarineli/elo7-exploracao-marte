package br.com.elo7.exploracao.modelo.comando;

import br.com.elo7.exploracao.modelo.VeiculoExploracao;

/**
 * Interface de comando.
 * 
 * @author talent.emarineli
 *
 */
public interface ComandoVeiculoExploracao {

	/**
	 * Executa um conjunto de comandos no veículo de exploração.
	 * 
	 * @param veiculoExploracao
	 *            veículo de exploração.
	 */
	public void execute(VeiculoExploracao veiculoExploracao);
}
