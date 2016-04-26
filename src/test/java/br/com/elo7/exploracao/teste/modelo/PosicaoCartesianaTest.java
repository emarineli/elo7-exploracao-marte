package br.com.elo7.exploracao.teste.modelo;

import static org.junit.Assert.*;
import org.junit.Test;

import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.*;
import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.EixoCartesiano.X;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.EixoCartesiano.Y;

/**
 * Classe de testes da Posicao Cartesiana.
 * 
 * @author emarineli
 *
 */
public class PosicaoCartesianaTest {

	private PosicaoCartesiana posicaoBase = new PosicaoCartesiana(2, 3);
	private static final int AVANCO_PADRAO = 1;

	/**
	 * Valida a posição cartesiana dada como padrão de implantação de uma sonda.
	 */
	@Test
	public void testPosicaoCartesianaPadrao() {
		assertEquals(new PosicaoCartesiana(0, 0), POSICAO_PADRAO);
	}

	/**
	 * Valida a posição cartesiana dada como padrão de implantação de uma sonda.
	 */
	@Test
	public void testIgualdadePosicao() {
		assertEquals(posicaoBase, new PosicaoCartesiana(2, 3));
	}

	/**
	 * Testa a tentativa de criação de uma posição cartesiana no eixo X tida
	 * como inválida para este exercício.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPosicaoCartesianaComEixoXInvalido() {
		new PosicaoCartesiana(-1, 0);
	}

	/**
	 * Testa a tentativa de criação de uma posição cartesiana no eixo Y tida
	 * como inválida para este exercício.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPosicaoCartesianaComEixoYInvalido() {
		new PosicaoCartesiana(0, -1);
	}

	/**
	 * Valida o avanço de uma unidade no eixo X sobre uma posição cartesiana já
	 * criada.
	 */
	@Test
	public void testAvancoNoEixoX() {
		assertEquals(posicaoBase, new PosicaoCartesiana(1, 3).avancarNoEixo(X, AVANCO_PADRAO));
	}

	/**
	 * Valida o avanço de uma unidade no eixo Y sobre uma posição cartesiana já
	 * criada.
	 */
	@Test
	public void testAvancoNoEixoY() {
		assertEquals(posicaoBase, new PosicaoCartesiana(2, 2).avancarNoEixo(Y, AVANCO_PADRAO));
	}

	/**
	 * Valida o retrocesso de uma unidade no eixo X sobre uma posição cartesiana já
	 * criada.
	 */
	@Test
	public void testRetrocessoNoEixoX() {
		assertEquals(posicaoBase, new PosicaoCartesiana(3, 3).retrocederNoEixo(X, AVANCO_PADRAO));
	}

	/**
	 * Valida o retrocesso de uma unidade no eixo Y sobre uma posição cartesiana já
	 * criada.
	 */
	@Test
	public void testRetrocessoNoEixoY() {
		assertEquals(posicaoBase, new PosicaoCartesiana(2, 4).retrocederNoEixo(Y, AVANCO_PADRAO));
	}
	
}
