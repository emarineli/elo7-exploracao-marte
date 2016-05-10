package br.com.elo7.exploracao.modelo.state;

import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.EixoCartesiano.X;
import br.com.elo7.exploracao.modelo.VeiculoExploracao;

/**
 * Etado de movimentação para o NORTE.
 * 
 * @author talent.emarineli
 *
 */
public class MovimentacaoLesteState implements MovimentacaoState {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mover(VeiculoExploracao veiculo) {

		veiculo.ajustarPosicaoAtual(veiculo.obterPosicaoAtual().avancarNoEixo(
				X, veiculo.obterAvancoPadrao()));

	}

}
