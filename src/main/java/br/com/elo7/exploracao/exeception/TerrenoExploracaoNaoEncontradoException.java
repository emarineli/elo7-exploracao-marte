package br.com.elo7.exploracao.exeception;

/**
 * Lançada quando não for possível encontrar uma sonda pelo seu identificador
 * interno.
 * 
 * @author emarineli
 *
 */
public class TerrenoExploracaoNaoEncontradoException extends RuntimeException {

	/**
	 * Serial gerado.
	 */
	private static final long serialVersionUID = 5981678401127787892L;

	public static final String MENSAGEM = "O Terreno de Exploração não pode ser encontrado!";

	public TerrenoExploracaoNaoEncontradoException() {
		super(MENSAGEM);
	}

}
