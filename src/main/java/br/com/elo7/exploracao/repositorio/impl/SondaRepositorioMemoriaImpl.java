package br.com.elo7.exploracao.repositorio.impl;

import java.util.HashMap;
import java.util.Map;

import br.com.elo7.exploracao.exeception.ColisaoSondaException;
import br.com.elo7.exploracao.exeception.SondaDuplicadaException;
import br.com.elo7.exploracao.exeception.SondaNaoEncontradaException;
import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
import br.com.elo7.exploracao.modelo.Sonda;
import br.com.elo7.exploracao.repositorio.SondaRepositorio;

/**
 * Implementação do repositório de Sondas. Esta implementação irá persistir os
 * objetos apenas em memória.
 * 
 * @author emarineli
 *
 */
public class SondaRepositorioMemoriaImpl implements SondaRepositorio {

	/* Os mapas abaixo servirão como repositórios em memória */
	private Map<String, Sonda> repoSondas;
	private Map<PosicaoCartesiana, String> repoPosicaoSondas;

	public SondaRepositorioMemoriaImpl() {
		this.repoSondas = new HashMap<String, Sonda>();
		this.repoPosicaoSondas = new HashMap<PosicaoCartesiana, String>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sonda implantarSonda(Sonda sonda) throws SondaDuplicadaException, ColisaoSondaException {

		if (this.repoSondas.containsKey(sonda.obterIdentificador())) {
			throw new SondaDuplicadaException(
					"A sonda com identificador [" + sonda.obterIdentificador() + "] já está implantada!");

		} else {

			if (this.repoPosicaoSondas.containsKey(sonda.obterPosicaoAtual())) {
				throw new ColisaoSondaException("A sonda com identificador [" + sonda.obterIdentificador()
						+ "] não será implantada para não colidir com sonda existente de identificador ["
						+ this.repoPosicaoSondas.get(sonda.obterPosicaoAtual()) + "]");

			}

			this.repoSondas.put(sonda.obterIdentificador(), sonda);
			this.repoPosicaoSondas.put(sonda.obterPosicaoAtual(), sonda.obterIdentificador());
		}

		return sonda;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removerSonda(String identificador) {

		if (this.repoSondas.containsKey(identificador)) {

			Sonda sonda = this.repoSondas.get(identificador);

			this.repoSondas.remove(identificador);
			this.repoPosicaoSondas.remove(sonda.obterPosicaoAtual());

			return true;
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sonda obterSondaPeloIdentificador(String identificador) throws SondaNaoEncontradaException {

		if (this.repoSondas.containsKey(identificador)) {
			return this.repoSondas.get(identificador);
		}

		throw new SondaNaoEncontradaException(
				"A Sonda com identificador [" + identificador + "] não pode ser encontrada!");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void atualizarPosicaoDirecaoSonda(Sonda sonda) throws SondaNaoEncontradaException, ColisaoSondaException {

		String identificadorSonda = sonda.obterIdentificador();

		if (this.repoSondas.containsKey(identificadorSonda)) {

			Sonda sondaAtual = this.repoSondas.get(identificadorSonda);

			/* Verifica se a posição foi alterada */
			if (!sondaAtual.obterPosicaoAtual().equals(sonda.obterPosicaoAtual())) {

				/* verifica se não vai haver colisão com outra sonda */
				if (this.repoPosicaoSondas.containsKey(sonda.obterPosicaoAtual())) {

					throw new ColisaoSondaException("A sonda com identificador [" + sonda.obterIdentificador()
							+ "] não será movimentada para não colidir com sonda existente de identificador ["
							+ this.repoPosicaoSondas.get(sonda.obterPosicaoAtual()) + "]");
				}

			}

			this.repoSondas.put(identificadorSonda, sonda);
			this.repoPosicaoSondas.put(sonda.obterPosicaoAtual(), identificadorSonda);

		} else {

			throw new SondaNaoEncontradaException(
					"A Sonda com identificador [" + identificadorSonda + "] não pode ser encontrada!");

		}

	}
}
