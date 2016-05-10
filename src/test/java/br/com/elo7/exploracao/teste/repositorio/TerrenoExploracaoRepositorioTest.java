package br.com.elo7.exploracao.teste.repositorio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.elo7.exploracao.exception.TerrenoExploracaoJaCriadoException;
import br.com.elo7.exploracao.exception.TerrenoExploracaoNaoEncontradoException;
import br.com.elo7.exploracao.modelo.TerrenoExploracao;
import br.com.elo7.exploracao.repositorio.TerrenoExploracaoRepositorio;
import br.com.elo7.exploracao.repositorio.impl.TerrenoExploracaoRepositorioImpl;

/**
 * Testes relacionados ao repositório do terreno de exploração.
 * 
 * @author talent.emarineli
 *
 */
public class TerrenoExploracaoRepositorioTest {

	private TerrenoExploracao terrenoBase = new TerrenoExploracao(5, 5);

	private TerrenoExploracaoRepositorio repo = new TerrenoExploracaoRepositorioImpl();

	/**
	 * Testa a criação de um terreno de exploração.
	 */
	@Test
	public void testCriarUmTerrenoExploracao() {
		repo.criarTerrenoExploracao(terrenoBase);

		assertEquals(terrenoBase, repo.obterTerrenoExploracao());
	}

	/**
	 * Testa a tentativa de criação de um terreno de exploração quando na
	 * verdade um outro já havia sido criado.
	 */
	@Test(expected = TerrenoExploracaoJaCriadoException.class)
	public void testCriarUmTerrenoExploracaoJaExistente() {
		repo.criarTerrenoExploracao(terrenoBase);
		repo.criarTerrenoExploracao(new TerrenoExploracao(6, 6));
	}

	/**
	 * Tenta criar um terreno de exploração.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriarUmTerrenoExploracaoNulo() {
		repo.criarTerrenoExploracao(null);

	}

	/**
	 * Tenta obter um terrno de exploração que ainda não foi criado.
	 */
	@Test(expected = TerrenoExploracaoNaoEncontradoException.class)
	public void testObterTerrnoExploracaoAindaNaoCriado() {
		repo.obterTerrenoExploracao();

	}

}
