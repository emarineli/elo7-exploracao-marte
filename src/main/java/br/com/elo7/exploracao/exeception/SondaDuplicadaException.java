package br.com.elo7.exploracao.exeception;

/**
 * Lançada quando for realizada a tentativa de se implantar uma sonda com um
 * identificador único já existente.
 * 
 * @author emarineli
 *
 */
public class SondaDuplicadaException extends RuntimeException {

	/**
	 * Serial gerado.
	 */
	private static final long serialVersionUID = 7424379811698911522L;

	public SondaDuplicadaException(String mensagem) {
		super(mensagem);
	}

}
