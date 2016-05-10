package br.com.elo7.exploracao.modelo.comando;

import java.util.stream.IntStream;

import org.springframework.util.Assert;

import br.com.elo7.exploracao.modelo.VeiculoExploracao;

/**
 * Comando responsável pela movimentação de um veículo em seu eixo um
 * determinado número de passos.
 * 
 * @author talent.emarineli
 *
 */
public class MovimentarVeiculoExploracaoPassosComando implements
		ComandoVeiculoExploracao {

	private IntStream numeroPassosStream;

	/**
	 * @param numeroPassos
	 *            número de passos de movimentação.
	 */
	public MovimentarVeiculoExploracaoPassosComando(int numeroPassos) {

		Assert.isTrue(numeroPassos >= 1,
				"O número de passo deve ser maior ou igual a um!");

		this.numeroPassosStream = IntStream.rangeClosed(1, numeroPassos);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(VeiculoExploracao veiculo) {

		// poderia fazer também veiculo::mover e criar um método em V.
		// Exploração -> mover(int)
		numeroPassosStream.forEach(Void -> veiculo.mover());
	}

}
