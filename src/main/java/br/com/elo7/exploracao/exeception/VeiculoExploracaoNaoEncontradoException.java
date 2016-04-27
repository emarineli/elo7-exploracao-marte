package br.com.elo7.exploracao.exeception;

/**
 * Lançada quando não for possível encontrar uma sonda pelo seu identificador
 * interno.
 * 
 * @author emarineli
 *
 */
public class VeiculoExploracaoNaoEncontradoException extends RuntimeException {

	/**
	 * Serial gerado.
	 */
	private static final long serialVersionUID = 5981678401127787892L;

	public VeiculoExploracaoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
}
