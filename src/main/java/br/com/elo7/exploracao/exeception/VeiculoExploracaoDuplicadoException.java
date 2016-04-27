package br.com.elo7.exploracao.exeception;

/**
 * Lançada quando for realizada a tentativa de se implantar uma sonda com um
 * identificador único já existente.
 * 
 * @author emarineli
 *
 */
public class VeiculoExploracaoDuplicadoException extends RuntimeException {

	/**
	 * Serial gerado.
	 */
	private static final long serialVersionUID = 7424379811698911522L;

	public static final String MENSAGEM = "O Veículo de Exploração com identificador [%s] já está implantado!";
	
	public VeiculoExploracaoDuplicadoException(String identificadorVeiculo) {
		super(String.format(MENSAGEM, identificadorVeiculo));
	}

}
