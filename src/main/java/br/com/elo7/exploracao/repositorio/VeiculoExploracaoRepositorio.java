package br.com.elo7.exploracao.repositorio;

import br.com.elo7.exploracao.exeception.ColisaoVeiculoExploracaoException;
import br.com.elo7.exploracao.exeception.VeiculoExploracaoDuplicadoException;
import br.com.elo7.exploracao.exeception.VeiculoExploracaoNaoEncontradoException;
import br.com.elo7.exploracao.modelo.VeiculoExploracao;

/**
 * Respnosável por expor as operações de repositório da entidade
 * VeiculoExploracao.
 * 
 * @author emarineli
 *
 */
public interface VeiculoExploracaoRepositorio {

	/**
	 * Operação responsável por implantar uma sonda.
	 * 
	 * @param veiculoExploracao
	 *            novo veículo que será implantado.
	 * 
	 * @return se o veículo foi implantado em caso de sucesso.
	 * 
	 * @throws VeiculoExploracaoDuplicadaException
	 *             caso o veículo que se está implantando já existir.
	 * 
	 * @throws ColisaoVeiculoExploracaoException
	 *             caso o veículo que se está implantando já estiver na mesma
	 *             posição de outra sonda.
	 */
	public VeiculoExploracao implantarVeiculoExploracao(
			VeiculoExploracao veiculoExploracao)
			throws VeiculoExploracaoDuplicadoException,
			ColisaoVeiculoExploracaoException;

	/**
	 * Remove um veículo de exploração. Esta opração é idempotente, portanto,
	 * caso o veículo for encontrada nenhum erro será lançado.
	 * 
	 * @param identificador
	 *            identificador do veículo de exploração.
	 * 
	 * @return indica que realmente existia um veículo e ele foi removido. Caso
	 *         não for encontrada nenhuma sonda para remoção, o retorno será
	 *         falso.
	 */
	public boolean removerVeiculoExploracao(String identificador);

	/**
	 * Recupera as informações de uma sonda pelo seu identificador interno.
	 * 
	 * @param identificador
	 *            identificador interno do veículo de exploração.
	 * @return representação do veículo caso for encontrado.
	 * @throws VeiculoExploracaoNaoEncontradaException
	 *             caso a sonda não for encontrada.
	 */
	public VeiculoExploracao obterVeiculoExploracaoPeloIdentificador(
			String identificador)
			throws VeiculoExploracaoNaoEncontradoException;

	/**
	 * Atualiza a posição e direção de uma sonda quando ela for movimentada.
	 * 
	 * @param veiculoExploracao
	 *            veículo com informações a serem atualizadas.
	 * 
	 * @throws VeiculoExploracaoNaoEncontradaException
	 *             caso o veículo não for encontrado.
	 * 
	 * @throws ColisaoVeiculoExploracaoException
	 *             caso o veículo que se está implantando já estiver na mesma
	 *             posição de outro veículo já implantado.
	 */
	public void atualizarPosicaoDirecaoVeiculoExploracao(
			VeiculoExploracao veiculoExploracao)
			throws VeiculoExploracaoNaoEncontradoException,
			ColisaoVeiculoExploracaoException;

}
