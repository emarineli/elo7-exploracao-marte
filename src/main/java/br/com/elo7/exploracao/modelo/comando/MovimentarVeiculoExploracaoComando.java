package br.com.elo7.exploracao.modelo.comando;

import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.EixoCartesiano.X;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.EixoCartesiano.Y;
import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
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

		int avanco = veiculo.obterAvancoPadrao();
		PosicaoCartesiana posicaoAtual = veiculo.obterPosicaoAtual();

		/* O eixo da movimentação irá depender da direção atual */
		switch (veiculo.obterDirecaoAtual()) {

		case NORTE:
			posicaoAtual = posicaoAtual.avancarNoEixo(Y, avanco);
			break;

		case SUL:
			posicaoAtual = posicaoAtual.retrocederNoEixo(Y, avanco);
			break;

		case LESTE:
			posicaoAtual = posicaoAtual.avancarNoEixo(X, avanco);
			break;

		case OESTE:
			posicaoAtual = posicaoAtual.retrocederNoEixo(X, avanco);
			break;
		}

		veiculo.ajustarPosicaoAtual(posicaoAtual);
	}

}
