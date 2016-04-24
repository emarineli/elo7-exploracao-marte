package br.com.elo7.exploracao.teste.sonda;

import static br.com.elo7.exploracao.modelo.DirecaoCardeal.DIRECAO_PADRAO;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.POSICAO_PADRAO;

import static org.junit.Assert.*;
import org.junit.Test;

import static br.com.elo7.exploracao.modelo.DirecaoCardeal.*;
import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
import br.com.elo7.exploracao.modelo.Sonda;

/**
 * Classe de testes sobre a entidade Sonda
 * 
 * @author emarineli
 *
 */
public class SondaTest {

	private Sonda sondaBase = new Sonda("sondaTeste", POSICAO_PADRAO, DIRECAO_PADRAO);

	/**
	 * Verifica a igualdade entre dois objetos.
	 */
	@Test
	public void testIgualdadeObjeto() {
		assertEquals(sondaBase, new Sonda("sondaTeste", POSICAO_PADRAO, DIRECAO_PADRAO));
	}

	/**
	 * Verifica a não igualdade entre dois objetos com identificadores
	 * distintos.
	 */
	@Test
	public void testNaoIgualdadeObjetoPorIdentificador() {
		assertNotEquals(sondaBase, new Sonda("sonda", POSICAO_PADRAO, DIRECAO_PADRAO));
	}

	/**
	 * Verifica a não igualdade entre dois objetos com posições cartesianas
	 * distintas.
	 */
	@Test
	public void testNaoIgualdadeObjetoPorPosicao() {
		assertNotEquals(sondaBase, new Sonda("sonda", new PosicaoCartesiana(1, 1), DIRECAO_PADRAO));
	}

	/**
	 * Verifica a não igualdade entre dois objetos com direções distintas.
	 */
	@Test
	public void testNaoIgualdadeObjetoPorDirecao() {
		assertNotEquals(sondaBase, new Sonda("sonda", POSICAO_PADRAO, SUL));
	}

	/**
	 * Verifica a tentativa de criação de uma sonda com um identificador nulo.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriacaoFalhaSondaPorIdentificadorNulo() {
		new Sonda(null, POSICAO_PADRAO, DIRECAO_PADRAO);
	}

	/**
	 * Verifica a tentativa de criação de uma sonda com um identificador vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriacaoFalhaSondaPorIdentificadorVazio() {
		new Sonda(" ", POSICAO_PADRAO, DIRECAO_PADRAO);
	}

	/**
	 * Verifica a tentativa de criação de uma sonda com uma posição nula.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriacaoFalhaSondaPorPosicaoNula() {
		new Sonda(" ", null, DIRECAO_PADRAO);
	}

	/**
	 * Verifica a tentativa de criação de uma sonda com uma direção nula.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriacaoFalhaSondaPorDirecaoNula() {
		new Sonda(" ", POSICAO_PADRAO, null);
	}

	/**
	 * Valida a obtenção da posição atual da sonda.
	 */
	@Test
	public void testIgualdadePosicaoAtual() {
		assertEquals(sondaBase.obterPosicaoAtual(), POSICAO_PADRAO);
	}

	/**
	 * Valida a obtenção da direção atual da sonda.
	 */
	@Test
	public void testIgualdadeDirecaoAtual() {
		assertEquals(sondaBase.obterDirecaoAtual(), DIRECAO_PADRAO);
	}

	/**
	 * Realiza um giro de 90 graus para a esquerda baseado na posição atual da
	 * sonda que será NORTE.
	 */
	@Test
	public void testGirarSondaParaEsquerdaComDirecaoAtualNorte() {

		Sonda sonda = new Sonda("teste", POSICAO_PADRAO, DIRECAO_PADRAO);
		sonda.girarParaEquerda();

		assertEquals(OESTE, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a esquerda baseado na posição atual da
	 * sonda que será SUL.
	 */
	@Test
	public void testGirarSondaParaEsquerdaComDirecaoAtualSul() {

		Sonda sonda = new Sonda("teste", POSICAO_PADRAO, SUL);
		sonda.girarParaEquerda();

		assertEquals(LESTE, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a esquerda baseado na posição atual da
	 * sonda que será LESTE.
	 */
	@Test
	public void testGirarSondaParaEsquerdaComDirecaoAtualLeste() {

		Sonda sonda = new Sonda("teste", POSICAO_PADRAO, LESTE);
		sonda.girarParaEquerda();

		assertEquals(NORTE, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a esquerda baseado na posição atual da
	 * sonda que será OESTE.
	 */
	@Test
	public void testGirarSondaParaEsquerdaComDirecaoAtualOeste() {

		Sonda sonda = new Sonda("teste", POSICAO_PADRAO, OESTE);
		sonda.girarParaEquerda();

		assertEquals(SUL, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a direita baseado na posição atual da
	 * sonda que será NORTE.
	 */
	@Test
	public void testGirarSondaParaDireitaComDirecaoAtualNorte() {

		Sonda sonda = new Sonda("teste", POSICAO_PADRAO, DIRECAO_PADRAO);
		sonda.girarParaDireita();

		assertEquals(LESTE, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a direita baseado na posição atual da
	 * sonda que será SUL.
	 */
	@Test
	public void testGirarSondaParaDireitaComDirecaoAtualSul() {

		Sonda sonda = new Sonda("teste", POSICAO_PADRAO, SUL);
		sonda.girarParaDireita();

		assertEquals(OESTE, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a direita baseado na posição atual da
	 * sonda que será LESTE.
	 */
	@Test
	public void testGirarSondaParaDireitaComDirecaoAtualLeste() {

		Sonda sonda = new Sonda("teste", POSICAO_PADRAO, LESTE);
		sonda.girarParaDireita();

		assertEquals(SUL, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a direita baseado na posição atual da
	 * sonda que será OESTE.
	 */
	@Test
	public void testGirarSondaParaDireitaComDirecaoAtualOeste() {

		Sonda sonda = new Sonda("teste", POSICAO_PADRAO, OESTE);
		sonda.girarParaDireita();

		assertEquals(NORTE, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza a movimentação da sonda em direção ao NORTE.
	 * 
	 * Neste cenário a sonda é posicionada para o NORTE, quando ela é
	 * movimentada com o avanço padrão ela deve ter um acrescimo de unidade no
	 * eixo Y.
	 */
	@Test
	public void testMovimentarSondaParaNorte() {

		Sonda sonda = new Sonda("teste", POSICAO_PADRAO, DIRECAO_PADRAO);
		sonda.movimentar();

		assertEquals(new PosicaoCartesiana(0, 1), sonda.obterPosicaoAtual());
	}

	/**
	 * Realiza a movimentação da sonda em direção ao SUL.
	 * 
	 * Neste cenário a sonda é posicionada para o SUL, quando ela é movimentada
	 * com o avanço padrão ela deve ter um decrescimo de unidade no eixo Y.
	 */
	@Test
	public void testMovimentarSondaParaSul() {

		Sonda sonda = new Sonda("teste", new PosicaoCartesiana(0, 2), SUL);
		sonda.movimentar();

		assertEquals(new PosicaoCartesiana(0, 1), sonda.obterPosicaoAtual());
	}

	/**
	 * Realiza a movimentação da sonda em direção ao LESTE.
	 * 
	 * Neste cenário a sonda é posicionada para o LESTE, quando ela é
	 * movimentada com o avanço padrão ela deve ter um acrescimo de unidade no
	 * eixo X.
	 */
	@Test
	public void testMovimentarSondaParaLeste() {

		Sonda sonda = new Sonda("teste", new PosicaoCartesiana(2, 2), LESTE);
		sonda.movimentar();

		assertEquals(new PosicaoCartesiana(3, 2), sonda.obterPosicaoAtual());
	}

	/**
	 * Realiza a movimentação da sonda em direção ao OESTE.
	 * 
	 * Neste cenário a sonda é posicionada para o OESTE, quando ela é
	 * movimentada com o avanço padrão ela deve ter um decrescimo de unidade no
	 * eixo Y.
	 */
	@Test
	public void testMovimentarSondaParaOeste() {

		Sonda sonda = new Sonda("teste", new PosicaoCartesiana(2, 2), OESTE);
		sonda.movimentar();

		assertEquals(new PosicaoCartesiana(1, 2), sonda.obterPosicaoAtual());
	}

}
