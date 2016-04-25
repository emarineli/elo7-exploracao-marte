package br.com.elo7.exploracao.teste.repositorio;

import org.junit.Test;

import br.com.elo7.exploracao.exeception.ColisaoSondaException;
import br.com.elo7.exploracao.exeception.SondaDuplicadaException;
import br.com.elo7.exploracao.exeception.SondaNaoEncontradaException;
import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
import br.com.elo7.exploracao.modelo.Sonda;
import br.com.elo7.exploracao.repositorio.impl.SondaRepositorioMemoriaImpl;

import static br.com.elo7.exploracao.modelo.DirecaoCardeal.*;

import static org.junit.Assert.*;

import org.junit.Before;

import static br.com.elo7.exploracao.modelo.DirecaoCardeal.DIRECAO_PADRAO;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.POSICAO_PADRAO;

/**
 * Testes relacionados ao repositório da entidade Sonda.
 * 
 * @author emarineli
 *
 */
public class SondaRepositorioMemoriaTest {

	private final Sonda sondaBase = new Sonda("teste", POSICAO_PADRAO, DIRECAO_PADRAO);
	private SondaRepositorioMemoriaImpl repo;

	@Before
	public void setup() {
		repo = new SondaRepositorioMemoriaImpl();
		repo.implantarSonda(sondaBase);
	}

	/**
	 * Realiza a obtenção com sucesso de uma sonda por seu identificador
	 * interno.
	 */
	@Test
	public void testObterSondaPorIdentificador() {
		assertEquals(sondaBase, repo.obterSondaPeloIdentificador("teste"));

	}

	/**
	 * Tenta obter uma sonda que ainda não foi implantada.
	 */
	@Test(expected = SondaNaoEncontradaException.class)
	public void testObterSondaNaoEncontradaPorIdentificador() {
		repo.obterSondaPeloIdentificador("identificadorNaoExistente");

	}

	/**
	 * Realiza a implantação com sucesso de uma sonda.
	 */
	@Test
	public void testImplantarSonda() {
		Sonda novaSonda = new Sonda("novaSonda", new PosicaoCartesiana(2, 2), LESTE);

		assertEquals(novaSonda, repo.implantarSonda(novaSonda));

	}

	/**
	 * Tenta realizar a implantação de uma sonda que já foi implantada.
	 */
	@Test(expected = SondaDuplicadaException.class)
	public void testImplantarSondaExistente() {
		repo.implantarSonda(sondaBase);

	}

	/**
	 * Tenta realizar a implantação de uma nova sonda sobre a mesma posição de
	 * outra já implantada.
	 */
	@Test(expected = ColisaoSondaException.class)
	public void testImplantarSondaColisaoPosicao() {
		repo.implantarSonda(new Sonda("novaSondaColisora", POSICAO_PADRAO, NORTE));

	}

	/**
	 * Atualiza com sucesso a posição de uma sonda implantada e que não colide
	 * com nenhum outra.
	 * 
	 * Neste caso, a direção continua a mesma.
	 */
	@Test
	public void testAtualizarPosicaoSonda() {

		Sonda sondaNovaPosicao = new Sonda(sondaBase.obterIdentificador(), new PosicaoCartesiana(2, 3), NORTE);

		repo.atualizarPosicaoDirecaoSonda(sondaNovaPosicao);

		assertEquals(sondaNovaPosicao, repo.obterSondaPeloIdentificador(sondaNovaPosicao.obterIdentificador()));
	}

	/**
	 * Atualiza com sucesso a direção de uma sonda implantada e que não colide
	 * com nenhum outra.
	 * 
	 * Neste caso, a posição continua a mesma e apenas a direção é alterada.
	 */
	@Test
	public void testAtualizarApenasDirecaoSonda() {

		Sonda sondaNovaPosicao = new Sonda(sondaBase.obterIdentificador(), sondaBase.obterPosicaoAtual(), SUL);

		repo.atualizarPosicaoDirecaoSonda(sondaNovaPosicao);

		assertEquals(sondaNovaPosicao, repo.obterSondaPeloIdentificador(sondaNovaPosicao.obterIdentificador()));
	}

	@Test(expected = ColisaoSondaException.class)
	public void testAtualizarSondaColisao() {

		/* Com o giro e movimentação a posição da sonda é alterada */
		sondaBase.girarParaDireita().movimentar();

		Sonda novaSontaColisora = repo.implantarSonda(new Sonda("novaSondaColisora", POSICAO_PADRAO, DIRECAO_PADRAO));
		novaSontaColisora.girarParaDireita().movimentar();

		repo.atualizarPosicaoDirecaoSonda(novaSontaColisora);

	}

	/**
	 * Tenta atualizar uma sonda que ainda não foi implantada.
	 */
	@Test(expected = SondaNaoEncontradaException.class)
	public void testAtualizarSondaNaoEncontrada() {

		Sonda sondaNaoImplantada = new Sonda("sondaNaoImplantada", POSICAO_PADRAO, DIRECAO_PADRAO);

		repo.atualizarPosicaoDirecaoSonda(sondaNaoImplantada);
	}

	/**
	 * Realiza a remoção de uma sonda já existente.
	 */
	@Test
	public void testRemoverSondaExistente() {

		String identificadorSonda = sondaBase.obterIdentificador();
		repo.removerSonda(identificadorSonda);

		try {
			repo.obterSondaPeloIdentificador(identificadorSonda);
			fail();
		} catch (SondaNaoEncontradaException e) {
			assertTrue(true); // :(
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Tenta remover uma sonda que não foi implantada. Neste caso a operação foi
	 * criada para ser idempotente e não retornará nenhum erro.
	 */
	@Test
	public void testRemoverSondaInexistente() {

		try {
			repo.removerSonda("identificadorInexistente");
		} catch (Exception e) {
			fail();
		}

	}
}
