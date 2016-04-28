package br.com.elo7.exploracao.modelo.comando;

import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.POSICAO_PADRAO;
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

		PosicaoCartesiana posicaoTerreno = veiculo
				.obterTerrenoExploracaoAssociado().obterExtensao();

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

		/* Verifica se a nova posição é inválida */
		if (!(posicaoAtual.getEixoY() > posicaoTerreno.getEixoY()
				|| posicaoAtual.getEixoX() > posicaoTerreno.getEixoX()
				|| posicaoAtual.getEixoY() < POSICAO_PADRAO.getEixoY() || posicaoAtual
				.getEixoX() < POSICAO_PADRAO.getEixoX())) {

			veiculo.ajustarPosicaoAtual(posicaoAtual);
		} else {
			throw new IllegalArgumentException(
					"A nova posição ultrapassa os limites do terreno. Ajuste os comandos!");
		}

	}

}
