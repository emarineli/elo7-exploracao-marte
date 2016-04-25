package br.com.elo7.exploracao.exeception;

/**
 * Lançada quando for realizada a tentativa de implantar uma sonda sobre uma já
 * existente.
 * 
 * @author emarineli
 *
 */
public class ColisaoSondaException extends RuntimeException {

	/**
	 * Serial gerado.
	 */
	private static final long serialVersionUID = 5981678401127787892L;

	public ColisaoSondaException(String mensagem) {
		super(mensagem);
	}
}
