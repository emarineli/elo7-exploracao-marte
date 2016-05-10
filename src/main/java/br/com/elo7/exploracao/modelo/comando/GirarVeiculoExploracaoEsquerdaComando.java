package br.com.elo7.exploracao.modelo.comando;

import br.com.elo7.exploracao.modelo.VeiculoExploracao;

/**
 * Comando Criado para Girar um Veículo de exploração.
 * 
 * @author emarineli
 *
 */
public class GirarVeiculoExploracaoEsquerdaComando implements ComandoVeiculoExploracao {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(VeiculoExploracao veiculoExploracao) {
		
		veiculoExploracao.girarEsquerda();

	}

}
