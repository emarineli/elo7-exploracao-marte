package br.com.elo7.exploracao.repositorio.impl;

import static org.springframework.util.Assert.notNull;

import org.springframework.stereotype.Repository;

import br.com.elo7.exploracao.exception.TerrenoExploracaoJaCriadoException;
import br.com.elo7.exploracao.exception.TerrenoExploracaoNaoEncontradoException;
import br.com.elo7.exploracao.modelo.TerrenoExploracao;
import br.com.elo7.exploracao.repositorio.TerrenoExploracaoRepositorio;

/**
 * Implementação do repositório de terrenos.
 * 
 * @author talent.emarineli
 *
 */
@Repository
public class TerrenoExploracaoRepositorioImpl implements
		TerrenoExploracaoRepositorio {

	private TerrenoExploracao terrenoExploracao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void criarTerrenoExploracao(TerrenoExploracao terrenoExploracao) {

		notNull(terrenoExploracao, "Terreno de exploração nulo");

		/* Não é permitida a alteração da referência */
		if (this.terrenoExploracao != null) {
			throw new TerrenoExploracaoJaCriadoException(this.terrenoExploracao);
		}

		this.terrenoExploracao = terrenoExploracao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TerrenoExploracao obterTerrenoExploracao()
			throws TerrenoExploracaoNaoEncontradoException {

		if (this.terrenoExploracao == null) {
			throw new TerrenoExploracaoNaoEncontradoException();
		}

		return this.terrenoExploracao;
	}

}
