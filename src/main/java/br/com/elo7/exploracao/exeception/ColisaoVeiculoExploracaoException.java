package br.com.elo7.exploracao.exeception;

/**
 * Lançada quando for realizada a tentativa de implantar uma sonda sobre uma já
 * existente.
 * 
 * @author emarineli
 *
 */
public class ColisaoVeiculoExploracaoException extends RuntimeException {

	/**
	 * Serial gerado.
	 */
	private static final long serialVersionUID = 5981678401127787892L;

	public static final String MENSAGEM = "O Veículo de Exploração com identificador [%s] não será movimentado para não"
			+ " colidir com outro Veículo de Exploração já existente de identificador [%s]";

	public ColisaoVeiculoExploracaoException(String identificadorVeiculoAtual,
			String identificadorVeiculoExistente) {
		super(String.format(MENSAGEM, identificadorVeiculoAtual,
				identificadorVeiculoExistente));
	}
}
