package br.com.elo7.exploracao;

/**
 * Representa qualquer veículo de exploração.
 * 
 * @author emarineli
 *
 */
public interface VeiculoExploracao {

	/**
	 * Realiza a movimentação do veículo.
	 * 
	 * A direção em que o veículo irá se movimentar depende de sua orientação da
	 * direção cardeal atual;
	 * 
	 * @return instância do veículo.
	 */
	VeiculoExploracao movimentar();

	/**
	 * Realiza um giro de 90 graus de sua direão cardeal para a esquerda.
	 * 
	 * @return instância do veículo.
	 */
	VeiculoExploracao girarParaEquerda();

	/**
	 * Realiza um giro de 90 graus de sua direção cardeal para a direita.
	 * 
	 * @return instância do veículo.
	 */
	VeiculoExploracao girarParaDireita();

}
