package br.com.elo7.exploracao.modelo.state;

import br.com.elo7.exploracao.modelo.VeiculoExploracao;

/**
 * Estados de movimentação da sonda.
 * 
 * @author talent.emarineli
 *
 */
public interface MovimentacaoState {

	public void mover(VeiculoExploracao veiculo);
	
}
