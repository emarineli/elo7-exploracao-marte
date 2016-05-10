package br.com.elo7.exploracao.modelo.comando;

import br.com.elo7.exploracao.modelo.VeiculoExploracao;

/**
 * Comando Criado para Girar um Veículo de exploração a 180 graus.
 * 
 * @author emarineli
 *
 */
public class GirarVeiculoExploracao180grausEsquerdaComando implements
		ComandoVeiculoExploracao {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(VeiculoExploracao veiculoExploracao) {

		veiculoExploracao.girar90GrausEsquerda().girar90GrausEsquerda();

	}

}
