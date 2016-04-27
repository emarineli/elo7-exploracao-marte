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

	public ColisaoVeiculoExploracaoException(String mensagem) {
		super(mensagem);
	}
}
