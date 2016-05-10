package br.com.elo7.exploracao.teste.modelo;

import static org.junit.Assert.*;
import org.junit.Test;

import static br.com.elo7.exploracao.modelo.DirecaoCardealEnum.*;

/**
 * Conjunto de testes relacionados ao enumerador de Direção Cardeal
 * 
 * @author emarineli
 *
 */
public class DirecaoCardealTest {

	/**
	 * Testa a próxima direção cardeal à esquerda com base na direção NORTE
	 */
	@Test
	public void testObterRepresentacaoProximaDirecaoEsquerdaNorte() {
		assertEquals(OESTE, NORTE.obterProximaDirecaoEsquerda().get());
	}

	/**
	 * Testa a próxima direção cardeal à esquerda com base na direção SUL
	 */
	@Test
	public void testObterRepresentacaoProximaDirecaoEsquerdaSul() {
		assertEquals(LESTE, SUL.obterProximaDirecaoEsquerda().get());
	}

	/**
	 * Testa a próxima direção cardeal à esquerda com base na direção LESTE
	 */
	@Test
	public void testObterRepresentacaoProximaDirecaoEsquerdaLeste() {
		assertEquals(NORTE, LESTE.obterProximaDirecaoEsquerda().get());
	}

	/**
	 * Testa a próxima direção cardeal à esquerda com base na direção OESTE
	 */
	@Test
	public void testObterRepresentacaoProximaDirecaoEsquerdaOeste() {
		assertEquals(SUL, OESTE.obterProximaDirecaoEsquerda().get());
	}

	/**
	 * Testa a próxima direção cardeal à direita com base na direção NORTE
	 */
	@Test
	public void testObterRepresentacaoProximaDirecaoDireitaNorte() {
		assertEquals(LESTE, NORTE.obterProximaDirecaoDireita().get());
	}

	/**
	 * Testa a próxima direção cardeal à direita com base na direção SUL
	 */
	@Test
	public void testObterRepresentacaoProximaDirecaoDireitaSul() {
		assertEquals(OESTE, SUL.obterProximaDirecaoDireita().get());
	}

	/**
	 * Testa a próxima direção cardeal à direita com base na direção LESTE
	 */
	@Test
	public void testObterRepresentacaoProximaDirecaoDireitaLeste() {
		assertEquals(SUL, LESTE.obterProximaDirecaoDireita().get());
	}

	/**
	 * Testa a próxima direção cardeal à direita com base na direção OESTE
	 */
	@Test
	public void testObterRepresentacaoProximaDirecaoDireitaOeste() {
		assertEquals(NORTE, OESTE.obterProximaDirecaoDireita().get());
	}

	/**
	 * Testa a obtenção da representação em string da direção norte.
	 */
	@Test
	public void testObterRepresentacaoStringNorte() {
		assertEquals("N", NORTE.obterRepresentacaoString());
	}

	/**
	 * Testa a obtenção da representação em string da direção sul.
	 */
	@Test
	public void testObterRepresentacaoStringSul() {
		assertEquals("S", SUL.obterRepresentacaoString());
	}

	/**
	 * Testa a obtenção da representação em string da direção leste.
	 */
	@Test
	public void testObterRepresentacaoStringLeste() {
		assertEquals("L", LESTE.obterRepresentacaoString());
	}

	/**
	 * Testa a obtenção da representação em string da direção leste.
	 */
	@Test
	public void testObterRepresentacaoStringOeste() {
		assertEquals("O", OESTE.obterRepresentacaoString());
	}

	/**
	 * Testa a obtenção da direção padrão.
	 */
	@Test
	public void testDirecaoCardealPadrao() {
		assertEquals(NORTE, DIRECAO_PADRAO);
	}

}
