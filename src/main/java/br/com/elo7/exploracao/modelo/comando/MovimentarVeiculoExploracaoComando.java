package br.com.elo7.exploracao.modelo.comando;

import br.com.elo7.exploracao.modelo.VeiculoExploracao;

/**
 * Comando responsável pela movimentação de um veículo em seu eixo.
 * 
 * @author talent.emarineli
 *
 */
public class MovimentarVeiculoExploracaoComando implements
		ComandoVeiculoExploracao {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(VeiculoExploracao veiculo) {

		veiculo.mover();
	}

}
