package br.com.elo7.exploracao.repositorio;

import br.com.elo7.exploracao.exception.TerrenoExploracaoNaoEncontradoException;
import br.com.elo7.exploracao.modelo.TerrenoExploracao;

/**
 * Interface de operações de repositório para entidade Terreno.
 * 
 * @author talent.emarineli
 *
 */
public interface TerrenoExploracaoRepositorio {

	/**
	 * Cria um terreno de exploração para a sonda.
	 * 
	 * Deverá existir apenas um único terreno criado para ser explorado pelas
	 * sondas que serão implantadas após sua criação.
	 * 
	 * @param terreno
	 *            terreno de exploração.
	 */
	public void criarTerrenoExploracao(TerrenoExploracao terreno);

	/**
	 * Obtém o terreno de exploração previamente criado.
	 * 
	 * @return terreno de exploração previamente criado.
	 * @throws TerrenoNaoEncontradoException
	 *             caso o terreno ainda não foi criado.
	 */
	public TerrenoExploracao obterTerrenoExploracao()
			throws TerrenoExploracaoNaoEncontradoException;
}
