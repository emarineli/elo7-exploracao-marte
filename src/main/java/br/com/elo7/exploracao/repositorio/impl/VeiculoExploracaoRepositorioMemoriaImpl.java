package br.com.elo7.exploracao.repositorio.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.elo7.exploracao.exeception.ColisaoVeiculoExploracaoException;
import br.com.elo7.exploracao.exeception.VeiculoExploracaoDuplicadoException;
import br.com.elo7.exploracao.exeception.VeiculoExploracaoNaoEncontradoException;
import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
import br.com.elo7.exploracao.modelo.VeiculoExploracao;
import br.com.elo7.exploracao.repositorio.VeiculoExploracaoRepositorio;

/**
 * Implementação do repositório de VeiculoExploracao. Esta implementação irá
 * persistir os objetos apenas em memória.
 * 
 * @author emarineli
 *
 */
@Repository
public class VeiculoExploracaoRepositorioMemoriaImpl implements
		VeiculoExploracaoRepositorio {

	/* Os mapas abaixo servirão como repositórios em memória */
	private Map<String, VeiculoExploracao> repoVeiculoExploracao;
	private Map<PosicaoCartesiana, String> repoPosicaoVeiculoExploracao;

	public VeiculoExploracaoRepositorioMemoriaImpl() {
		this.repoVeiculoExploracao = new HashMap<String, VeiculoExploracao>();
		this.repoPosicaoVeiculoExploracao = new HashMap<PosicaoCartesiana, String>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VeiculoExploracao implantarVeiculoExploracao(
			VeiculoExploracao veiculoExploracao)
			throws VeiculoExploracaoDuplicadoException,
			ColisaoVeiculoExploracaoException {

		if (this.repoVeiculoExploracao.containsKey(veiculoExploracao
				.obterIdentificador())) {
			throw new VeiculoExploracaoDuplicadoException(
					veiculoExploracao.obterIdentificador());

		} else {

			if (this.repoPosicaoVeiculoExploracao.containsKey(veiculoExploracao
					.obterPosicaoAtual())) {
				throw new ColisaoVeiculoExploracaoException(
						veiculoExploracao.obterIdentificador(),
						this.repoPosicaoVeiculoExploracao.get(veiculoExploracao
								.obterPosicaoAtual()));

			}

			this.repoVeiculoExploracao.put(
					veiculoExploracao.obterIdentificador(), veiculoExploracao);
			this.repoPosicaoVeiculoExploracao.put(
					veiculoExploracao.obterPosicaoAtual(),
					veiculoExploracao.obterIdentificador());
		}

		return veiculoExploracao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removerVeiculoExploracao(String identificador) {

		if (this.repoVeiculoExploracao.containsKey(identificador)) {

			VeiculoExploracao veiculoExploracao = this.repoVeiculoExploracao
					.get(identificador);

			this.repoVeiculoExploracao.remove(identificador);
			this.repoPosicaoVeiculoExploracao.remove(veiculoExploracao
					.obterPosicaoAtual());

			return true;
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VeiculoExploracao obterVeiculoExploracaoPeloIdentificador(
			String identificador)
			throws VeiculoExploracaoNaoEncontradoException {

		if (this.repoVeiculoExploracao.containsKey(identificador)) {
			return this.repoVeiculoExploracao.get(identificador);
		}

		throw new VeiculoExploracaoNaoEncontradoException(identificador);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void atualizarPosicaoDirecaoVeiculoExploracao(
			VeiculoExploracao veiculoExploracao)
			throws VeiculoExploracaoNaoEncontradoException,
			ColisaoVeiculoExploracaoException {

		String identificadorVeiculoExploracao = veiculoExploracao
				.obterIdentificador();

		if (this.repoVeiculoExploracao
				.containsKey(identificadorVeiculoExploracao)) {

			VeiculoExploracao veiculoAtual = this.repoVeiculoExploracao
					.get(identificadorVeiculoExploracao);

			/* Verifica se a posição foi alterada */
			if (!veiculoAtual.obterPosicaoAtual().equals(
					veiculoExploracao.obterPosicaoAtual())) {

				/* verifica se não vai haver colisão com outro veículo */
				if (this.repoPosicaoVeiculoExploracao
						.containsKey(veiculoExploracao.obterPosicaoAtual())) {

					throw new ColisaoVeiculoExploracaoException(
							veiculoExploracao.obterIdentificador(),
							this.repoPosicaoVeiculoExploracao
									.get(veiculoExploracao.obterPosicaoAtual()));
				}

			}

			this.repoVeiculoExploracao.put(identificadorVeiculoExploracao,
					veiculoExploracao);
			this.repoPosicaoVeiculoExploracao.put(
					veiculoExploracao.obterPosicaoAtual(),
					identificadorVeiculoExploracao);

		} else {

			throw new VeiculoExploracaoNaoEncontradoException(
					identificadorVeiculoExploracao);

		}

	}
}
