package br.com.elo7.exploracao.exeception;

import br.com.elo7.exploracao.modelo.TerrenoExploracao;

/**
 * Lançada quando não for possível encontrar uma sonda pelo seu identificador
 * interno.
 * 
 * @author emarineli
 *
 */
public class TerrenoExploracaoJaCriadoException extends RuntimeException {

	/**
	 * Serial gerado.
	 */
	private static final long serialVersionUID = 1719167538376903866L;

	public static final String MENSAGEM = "O Terreno para exploração das sondas já foi criado e definido com as dimensões em X [%s] e Y [%s]";

	public TerrenoExploracaoJaCriadoException(TerrenoExploracao terreno) {
		super(String.format(MENSAGEM, terreno.obterExtensao().getEixoX(),
				terreno.obterExtensao().getEixoY()));
	}

}
