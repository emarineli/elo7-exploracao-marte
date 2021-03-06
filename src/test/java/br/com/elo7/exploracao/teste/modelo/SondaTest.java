package br.com.elo7.exploracao.teste.modelo;

import static br.com.elo7.exploracao.modelo.DirecaoCardealEnum.DIRECAO_PADRAO;
import static br.com.elo7.exploracao.modelo.DirecaoCardealEnum.LESTE;
import static br.com.elo7.exploracao.modelo.DirecaoCardealEnum.NORTE;
import static br.com.elo7.exploracao.modelo.DirecaoCardealEnum.OESTE;
import static br.com.elo7.exploracao.modelo.DirecaoCardealEnum.SUL;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.POSICAO_PADRAO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
import br.com.elo7.exploracao.modelo.SondaSimples;
import br.com.elo7.exploracao.modelo.TerrenoExploracao;
import br.com.elo7.exploracao.modelo.comando.GirarVeiculoExploracaoDireitaComando;
import br.com.elo7.exploracao.modelo.comando.GirarVeiculoExploracaoEsquerdaComando;
import br.com.elo7.exploracao.modelo.comando.MovimentarVeiculoExploracaoComando;
import br.com.elo7.exploracao.repositorio.impl.VeiculoExploracaoRepositorioMemoriaImpl;

/**
 * Classe de testes sobre a entidade Sonda
 * 
 * @author emarineli
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SondaTest {

	@Mock
	private VeiculoExploracaoRepositorioMemoriaImpl repoMock;

	private final SondaSimples sondaBase = new SondaSimples("sondaTeste", POSICAO_PADRAO, DIRECAO_PADRAO);

	private final TerrenoExploracao terrenoBase = new TerrenoExploracao(10, 10);

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Verifica a igualdade entre dois objetos.
	 */
	@Test
	public void testIgualdadeObjeto() {
		assertEquals(sondaBase, new SondaSimples("sondaTeste", POSICAO_PADRAO, DIRECAO_PADRAO));
	}

	/**
	 * Verifica a não igualdade entre dois objetos com identificadores
	 * distintos.
	 */
	@Test
	public void testNaoIgualdadeObjetoPorIdentificador() {
		assertNotEquals(sondaBase, new SondaSimples("sonda", POSICAO_PADRAO, DIRECAO_PADRAO));
	}

	/**
	 * Verifica a não igualdade entre dois objetos com posições cartesianas
	 * distintas.
	 */
	@Test
	public void testNaoIgualdadeObjetoPorPosicao() {
		assertNotEquals(sondaBase, new SondaSimples("sonda", new PosicaoCartesiana(1, 1), DIRECAO_PADRAO));
	}

	/**
	 * Verifica a não igualdade entre dois objetos com direções distintas.
	 */
	@Test
	public void testNaoIgualdadeObjetoPorDirecao() {
		assertNotEquals(sondaBase, new SondaSimples("sonda", POSICAO_PADRAO, SUL));
	}

	/**
	 * Verifica a tentativa de criação de uma sonda com um identificador nulo.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriacaoFalhaSondaPorIdentificadorNulo() {
		new SondaSimples(null, POSICAO_PADRAO, DIRECAO_PADRAO);
	}

	/**
	 * Verifica a tentativa de criação de uma sonda com um identificador vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriacaoFalhaSondaPorIdentificadorVazio() {
		new SondaSimples(" ", POSICAO_PADRAO, DIRECAO_PADRAO);
	}

	/**
	 * Verifica a tentativa de criação de uma sonda com uma posição nula.
	 * 
	 * Caso não seja informada uma posição, a posição padrão (0,0) é atribuída.
	 */
	@Test
	public void testCriacaoSondaPorPosicaoNula() {
		assertNotEquals(sondaBase, new SondaSimples("sonda", null, NORTE));
	}

	/**
	 * Verifica a tentativa de criação de uma sonda com uma direção nula.
	 * 
	 * Caso não seja informada uma direção, a direção padrão (NORTE) é
	 * atribuída.
	 */
	@Test
	public void testCriacaoSondaPorDirecaoNula() {
		assertNotEquals(sondaBase, new SondaSimples("sonda", POSICAO_PADRAO, null));
	}

	/**
	 * Verifica a tentativa de criação de uma sonda com uma direção nula.
	 * 
	 * Caso direção e posição não sejam informadas, seus valores padrão são
	 * atribuídos.
	 */
	@Test
	public void testCriacaoSondaPorDirecaoEPosicaoNula() {
		assertNotEquals(sondaBase, new SondaSimples("sonda", null, null));
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

	@Test
	public void testAssociarTerrenoExploracao() {
		sondaBase.associarTerrenoExploracao(terrenoBase);
		assertEquals(terrenoBase, sondaBase.obterTerrenoExploracaoAssociado());
	}

	/**
	 * Realiza um giro de 90 graus para a esquerda baseado na posição atual da
	 * sonda que será NORTE.
	 */
	@Test
	public void testGirarSondaParaEsquerdaComDirecaoAtualNorte() {

		SondaSimples sonda = new SondaSimples("teste", POSICAO_PADRAO, DIRECAO_PADRAO);
		sonda.setVeiculoExploracaoRepositorio(repoMock);

		sonda.processarComando(new GirarVeiculoExploracaoEsquerdaComando());

		assertEquals(OESTE, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a esquerda baseado na posição atual da
	 * sonda que será SUL.
	 */
	@Test
	public void testGirarSondaParaEsquerdaComDirecaoAtualSul() {

		SondaSimples sonda = new SondaSimples("teste", POSICAO_PADRAO, SUL);
		sonda.setVeiculoExploracaoRepositorio(repoMock);

		sonda.processarComando(new GirarVeiculoExploracaoEsquerdaComando());

		assertEquals(LESTE, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a esquerda baseado na posição atual da
	 * sonda que será LESTE.
	 */
	@Test
	public void testGirarSondaParaEsquerdaComDirecaoAtualLeste() {

		SondaSimples sonda = new SondaSimples("teste", POSICAO_PADRAO, LESTE);
		sonda.setVeiculoExploracaoRepositorio(repoMock);

		sonda.processarComando(new GirarVeiculoExploracaoEsquerdaComando());

		assertEquals(NORTE, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a esquerda baseado na posição atual da
	 * sonda que será OESTE.
	 */
	@Test
	public void testGirarSondaParaEsquerdaComDirecaoAtualOeste() {

		SondaSimples sonda = new SondaSimples("teste", POSICAO_PADRAO, OESTE);
		sonda.setVeiculoExploracaoRepositorio(repoMock);

		sonda.processarComando(new GirarVeiculoExploracaoEsquerdaComando());

		assertEquals(SUL, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a direita baseado na posição atual da
	 * sonda que será NORTE.
	 */
	@Test
	public void testGirarSondaParaDireitaComDirecaoAtualNorte() {

		SondaSimples sonda = new SondaSimples("teste", POSICAO_PADRAO, DIRECAO_PADRAO);
		sonda.setVeiculoExploracaoRepositorio(repoMock);

		sonda.processarComando(new GirarVeiculoExploracaoDireitaComando());

		assertEquals(LESTE, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a direita baseado na posição atual da
	 * sonda que será SUL.
	 */
	@Test
	public void testGirarSondaParaDireitaComDirecaoAtualSul() {

		SondaSimples sonda = new SondaSimples("teste", POSICAO_PADRAO, SUL);
		sonda.setVeiculoExploracaoRepositorio(repoMock);

		sonda.processarComando(new GirarVeiculoExploracaoDireitaComando());

		assertEquals(OESTE, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a direita baseado na posição atual da
	 * sonda que será LESTE.
	 */
	@Test
	public void testGirarSondaParaDireitaComDirecaoAtualLeste() {

		SondaSimples sonda = new SondaSimples("teste", POSICAO_PADRAO, LESTE);
		sonda.setVeiculoExploracaoRepositorio(repoMock);

		sonda.processarComando(new GirarVeiculoExploracaoDireitaComando());

		assertEquals(SUL, sonda.obterDirecaoAtual());
	}

	/**
	 * Realiza um giro de 90 graus para a direita baseado na posição atual da
	 * sonda que será OESTE.
	 */
	@Test
	public void testGirarSondaParaDireitaComDirecaoAtualOeste() {

		SondaSimples sonda = new SondaSimples("teste", POSICAO_PADRAO, OESTE);
		sonda.setVeiculoExploracaoRepositorio(repoMock);

		sonda.processarComando(new GirarVeiculoExploracaoDireitaComando());

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

		SondaSimples sonda = new SondaSimples("teste", POSICAO_PADRAO, DIRECAO_PADRAO);
		sonda.setVeiculoExploracaoRepositorio(repoMock);
		sonda.associarTerrenoExploracao(terrenoBase);

		sonda.processarComando(new MovimentarVeiculoExploracaoComando());

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

		SondaSimples sonda = new SondaSimples("teste", new PosicaoCartesiana(0, 2), SUL);
		sonda.setVeiculoExploracaoRepositorio(repoMock);
		sonda.associarTerrenoExploracao(terrenoBase);

		sonda.processarComando(new MovimentarVeiculoExploracaoComando());

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

		SondaSimples sonda = new SondaSimples("teste", new PosicaoCartesiana(2, 2), LESTE);
		sonda.setVeiculoExploracaoRepositorio(repoMock);
		sonda.associarTerrenoExploracao(terrenoBase);

		sonda.processarComando(new MovimentarVeiculoExploracaoComando());

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

		SondaSimples sonda = new SondaSimples("teste", new PosicaoCartesiana(2, 2), OESTE);
		sonda.setVeiculoExploracaoRepositorio(repoMock);
		sonda.associarTerrenoExploracao(terrenoBase);

		sonda.processarComando(new MovimentarVeiculoExploracaoComando());

		assertEquals(new PosicaoCartesiana(1, 2), sonda.obterPosicaoAtual());
	}

	/**
	 * Realiza o teste de caso apresentado no enunciado do problema.
	 * 
	 * ENTRADA:
	 * 
	 * 1 2 N LMLMLMLMM
	 * 
	 * SAIDA:
	 * 
	 * 1 3 N
	 */
	@Test
	public void testCasoPrimeiroExemploExercicio() {

		SondaSimples sonda = new SondaSimples("teste", new PosicaoCartesiana(1, 2), NORTE);
		sonda.setVeiculoExploracaoRepositorio(repoMock);
		sonda.associarTerrenoExploracao(terrenoBase);

		GirarVeiculoExploracaoEsquerdaComando girarEsquerda = new GirarVeiculoExploracaoEsquerdaComando();
		MovimentarVeiculoExploracaoComando movimentar = new MovimentarVeiculoExploracaoComando();

		sonda.processarComandos(girarEsquerda, movimentar, girarEsquerda, movimentar, girarEsquerda, movimentar,
				girarEsquerda, movimentar, movimentar);

		assertEquals(new PosicaoCartesiana(1, 3), sonda.obterPosicaoAtual());
		assertEquals(NORTE, sonda.obterDirecaoAtual());

	}

	/**
	 * Realiza o teste de caso apresentado no enunciado do problema.
	 * 
	 * ENTRADA:
	 * 
	 * 3 3 E MMRMMRMRRM
	 * 
	 * SAIDA:
	 * 
	 * 5 1 E
	 */
	@Test
	public void testCasoSegundoExemploExercicio() {

		SondaSimples sonda = new SondaSimples("teste", new PosicaoCartesiana(3, 3), LESTE);
		sonda.setVeiculoExploracaoRepositorio(repoMock);
		sonda.associarTerrenoExploracao(terrenoBase);

		GirarVeiculoExploracaoDireitaComando girarDireita = new GirarVeiculoExploracaoDireitaComando();
		MovimentarVeiculoExploracaoComando movimentar = new MovimentarVeiculoExploracaoComando();

		sonda.processarComandos(movimentar, movimentar, girarDireita, movimentar, movimentar, girarDireita, movimentar,
				girarDireita, girarDireita, movimentar);

		assertEquals(new PosicaoCartesiana(5, 1), sonda.obterPosicaoAtual());
		assertEquals(LESTE, sonda.obterDirecaoAtual());

	}

	/**
	 * Testa a tentativa de se implantar a sonda fora da dimensão do eixo X no
	 * terreno de implantação.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testImplantarSondaForaDimensaoTerrenoEixoX() {
		SondaSimples sonda = new SondaSimples("teste", new PosicaoCartesiana(11, 3), LESTE);
		sonda.associarTerrenoExploracao(terrenoBase);
	}

	/**
	 * Testa a tentativa de se implantar a sonda fora da dimensão do eixo Y no
	 * terreno de implantação.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testImplantarSondaForaDimensaoTerrenoEixoY() {
		SondaSimples sonda = new SondaSimples("teste", new PosicaoCartesiana(5, 11), LESTE);
		sonda.associarTerrenoExploracao(terrenoBase);
	}

	/**
	 * Realiza o teste sobre o avanço da sonda no eixo Y para sobre o terreno
	 * demarcado.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMovimentarSondaSobreTerrenoAvancoY() {

		SondaSimples sonda = new SondaSimples("teste", new PosicaoCartesiana(1, 2), NORTE);
		sonda.setVeiculoExploracaoRepositorio(repoMock);
		sonda.associarTerrenoExploracao(new TerrenoExploracao(3, 3));

		MovimentarVeiculoExploracaoComando movimentar = new MovimentarVeiculoExploracaoComando();

		sonda.processarComandos(movimentar, movimentar, movimentar, movimentar);
	}

	/**
	 * Realiza o teste sobre o avanço da sonda no eixo X para sobre o terreno
	 * demarcado.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMovimentarSondaSobreTerrenoAvancoX() {

		SondaSimples sonda = new SondaSimples("teste", new PosicaoCartesiana(1, 2), LESTE);
		sonda.setVeiculoExploracaoRepositorio(repoMock);
		sonda.associarTerrenoExploracao(new TerrenoExploracao(3, 3));

		MovimentarVeiculoExploracaoComando movimentar = new MovimentarVeiculoExploracaoComando();

		sonda.processarComandos(movimentar, movimentar, movimentar, movimentar);
	}

	/**
	 * Realiza o teste sobre o retrocesso da sonda no eixo Y para sobre o
	 * terreno demarcado.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMovimentarSondaSobreTerrenoRetrocessoY() {

		SondaSimples sonda = new SondaSimples("teste", new PosicaoCartesiana(1, 2), SUL);
		sonda.setVeiculoExploracaoRepositorio(repoMock);
		sonda.associarTerrenoExploracao(new TerrenoExploracao(3, 3));

		MovimentarVeiculoExploracaoComando movimentar = new MovimentarVeiculoExploracaoComando();

		sonda.processarComandos(movimentar, movimentar, movimentar);
	}

	/**
	 * Realiza o teste sobre o retrocesso da sonda no eixo Y para sobre o
	 * terreno demarcado.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMovimentarSondaSobreTerrenoRetrocessoX() {

		SondaSimples sonda = new SondaSimples("teste", new PosicaoCartesiana(1, 2), OESTE);
		sonda.setVeiculoExploracaoRepositorio(repoMock);
		sonda.associarTerrenoExploracao(new TerrenoExploracao(3, 3));

		MovimentarVeiculoExploracaoComando movimentar = new MovimentarVeiculoExploracaoComando();

		sonda.processarComandos(movimentar, movimentar);
	}

}
