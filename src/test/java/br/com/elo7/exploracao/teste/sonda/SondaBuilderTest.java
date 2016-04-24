package br.com.elo7.exploracao.teste.sonda;

import static br.com.elo7.exploracao.modelo.DirecaoCardeal.DIRECAO_PADRAO;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.POSICAO_PADRAO;

import static org.junit.Assert.*;
import org.junit.Test;

import br.com.elo7.exploracao.SondaBuilder;
import br.com.elo7.exploracao.modelo.Sonda;

/**
 * Testes sobre a entidade Sonda
 * 
 * @author emarineli
 *
 */
public class SondaBuilderTest {

	private Sonda sondaBase = new Sonda("sondaTeste", POSICAO_PADRAO, DIRECAO_PADRAO);

	/**
	 * Utiliza um builder de sonda para criar um objeto do tipo Sonda de forma
	 * padronizada.
	 * 
	 * Para posição e direção serão informados os valores padrão.
	 * 
	 */
	@Test
	public void testBuilderPadraoInformandoDirecaoEPosicao() {
		SondaBuilder builder = new SondaBuilder();

		Sonda sondaEsperada = builder.comIdentificador("sondaTeste").naDirecaoInicial(DIRECAO_PADRAO)
				.naPosicaoInicial(POSICAO_PADRAO).criarSonda();

		assertEquals(sondaBase, sondaEsperada);
	}

	/**
	 * Utiliza um builder de sonda para criar um objeto do tipo Sonda de forma
	 * padronizada.
	 * 
	 * Para posição e direção serão informados os valores padrão.
	 * 
	 */
	@Test
	public void testBuilderPadraoInformandoDirecaoEPosicaoPorEixo() {
		SondaBuilder builder = new SondaBuilder();

		Sonda sondaEsperada = builder.comIdentificador("sondaTeste").naDirecaoInicial(DIRECAO_PADRAO)
				.naPosicaoInicial(0, 0).criarSonda();

		assertEquals(sondaBase, sondaEsperada);
	}

	/**
	 * Utiliza um builder de sonda para criar um objeto do tipo Sonda de forma
	 * padronizada.
	 * 
	 * Para posição e direção serão informados os valores padrão.
	 * 
	 */
	@Test
	public void testBuilderPadraoInformandoIdentificadorGerado() {
		SondaBuilder builder = new SondaBuilder();

		Sonda sondaEsperada = builder.comIdentificadorUUIDGerado().naDirecaoInicial(DIRECAO_PADRAO)
				.naPosicaoInicial(0, 0).criarSonda();

		assertNotNull(sondaEsperada.obterIdentificador());
		assertTrue(sondaEsperada.obterIdentificador().length() > 0);
	}

	/**
	 * Utiliza o builder para criar um objeto do tipo Sonda de forma
	 * padronizada.
	 * 
	 * Posição e direção no builder são padrões.
	 * 
	 */
	@Test
	public void testBuilderComDirecaoEPosicaoPadronizadas() {
		SondaBuilder builder = new SondaBuilder();

		Sonda sondaEsperada = builder.comIdentificador("sondaTeste").naDirecaoPadraoInicial().naPosicaoPadraoInicial()
				.criarSonda();

		assertEquals(sondaBase, sondaEsperada);
	}

	/**
	 * Tenta realizar a criação de uma sonda sem informar um identificador para
	 * ela.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testBuilderFalhaIdentificador() {
		new SondaBuilder().naDirecaoPadraoInicial().naPosicaoPadraoInicial().criarSonda();
	}

	/**
	 * Tenta realizar a criação de uma sonda sem informar uma direção inicial
	 * para ela.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testBuilderFalhaDirecao() {
		new SondaBuilder().comIdentificador("sondaTeste").naPosicaoPadraoInicial().criarSonda();
	}

	/**
	 * Tenta realizar a criação de uma sonda sem informar uma direção inicial
	 * para ela.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testBuilderFalhaPosicao() {
		new SondaBuilder().comIdentificador("sondaTeste").naDirecaoPadraoInicial().criarSonda();
	}

}
