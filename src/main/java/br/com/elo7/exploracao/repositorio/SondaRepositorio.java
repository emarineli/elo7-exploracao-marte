package br.com.elo7.exploracao.repositorio;

import br.com.elo7.exploracao.exeception.ColisaoSondaException;
import br.com.elo7.exploracao.exeception.SondaDuplicadaException;
import br.com.elo7.exploracao.exeception.SondaNaoEncontradaException;
import br.com.elo7.exploracao.modelo.Sonda;

/**
 * Respnosável por expor as operações de repositório da entidade Sonda.
 * 
 * @author emarineli
 *
 */
public interface SondaRepositorio {

	/**
	 * Operação responsável por implantar uma sonda.
	 * 
	 * @param sonda
	 *            sonda que será implantada.
	 * 
	 * @return a sonda implantada em caso de sucesso.
	 * 
	 * @throws SondaDuplicadaException
	 *             caso a sonda que se está implantando já existir.
	 * 
	 * @throws ColisaoSondaException
	 *             caso a sonda que se está implantando já estiver na mesma
	 *             posição de outra sonda.
	 */
	public Sonda implantarSonda(Sonda sonda) throws SondaDuplicadaException, ColisaoSondaException;

	/**
	 * Remove uma sonda. Esta opração é idempotente, portanto, caso a sonda for
	 * encontrada nenhum erro será lançado.
	 * 
	 * @param identificador
	 *            identificador da sonda.
	 * 
	 * @return indica que realmente existia uma sonda e que ela foi removida.
	 *         Caso não for encontrada nenhuma sonda para remoção, o retorno
	 *         será falso.
	 */
	public boolean removerSonda(String identificador);

	/**
	 * Recupera as informações de uma sonda pelo seu identificador interno.
	 * 
	 * @param identificador
	 *            identificador interno da sonda.
	 * @return representação da sonda caso for encontrada.
	 * @throws SondaNaoEncontradaException
	 *             caso a sonda não for encontrada.
	 */
	public Sonda obterSondaPeloIdentificador(String identificador) throws SondaNaoEncontradaException;

	/**
	 * Atualiza a posição e direção de uma sonda quando ela for movimentada.
	 * 
	 * @param sonda
	 *            sonda com informações a serem atualizadas.
	 * 
	 * @throws SondaNaoEncontradaException
	 *             caso a sonda não for encontrada.
	 * 
	 * @throws ColisaoSondaException
	 *             caso a sonda que se está implantando já estiver na mesma
	 *             posição de outra sonda.
	 */
	public void atualizarPosicaoDirecaoSonda(Sonda sonda) throws SondaNaoEncontradaException, ColisaoSondaException;

}
