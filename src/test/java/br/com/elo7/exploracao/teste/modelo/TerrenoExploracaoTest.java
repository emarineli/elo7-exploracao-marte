package br.com.elo7.exploracao.teste.modelo;

import org.junit.Test;
import static org.junit.Assert.*;

import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
import br.com.elo7.exploracao.modelo.TerrenoExploracao;

/**
 * Testes relacionados à entidade Terreno.
 * 
 * @author talent.emarineli
 *
 */
public class TerrenoExploracaoTest {

	TerrenoExploracao terrenoBase = new TerrenoExploracao(10, 5);

	/**
	 * Verifica igualdade entre dois objetos distintos.
	 */
	@Test
	public void testIgualdadeObjetos() {
		assertEquals(terrenoBase, new TerrenoExploracao(10, 5));
	}

	/**
	 * Verifica igualdade da posição cartesiana obtida.
	 */
	@Test
	public void testIgualdadeObtencaoExtensao() {
		assertEquals(terrenoBase.obterExtensao(), new PosicaoCartesiana(10, 5));
	}

	/**
	 * Realiza a tentativa de criar um terreno com suas dimensões mínimas, o que
	 * não é permitido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriacaoTerrenoPosicaoMinima() {
		new TerrenoExploracao(0, 0);
	}

}
