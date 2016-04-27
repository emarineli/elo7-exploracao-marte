package br.com.elo7.exploracao.teste.repositorio;

import static br.com.elo7.exploracao.modelo.DirecaoCardeal.DIRECAO_PADRAO;
import static br.com.elo7.exploracao.modelo.DirecaoCardeal.LESTE;
import static br.com.elo7.exploracao.modelo.DirecaoCardeal.NORTE;
import static br.com.elo7.exploracao.modelo.DirecaoCardeal.SUL;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.POSICAO_PADRAO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.elo7.exploracao.exeception.ColisaoVeiculoExploracaoException;
import br.com.elo7.exploracao.exeception.VeiculoExploracaoDuplicadoException;
import br.com.elo7.exploracao.exeception.VeiculoExploracaoNaoEncontradoException;
import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
import br.com.elo7.exploracao.modelo.SondaSimples;
import br.com.elo7.exploracao.repositorio.impl.VeiculoExploracaoRepositorioMemoriaImpl;

/**
 * Testes relacionados ao repositório da entidade de Veículo de Exploração.
 * 
 * Estes testes são realizados com uma sonda simples
 * 
 * @author emarineli
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class VeiculoExploracaoRepositorioMemoriaTest {

	private VeiculoExploracaoRepositorioMemoriaImpl repo;

	@Mock
	private VeiculoExploracaoRepositorioMemoriaImpl repoMock;

	private final SondaSimples sondaBase = new SondaSimples("teste", POSICAO_PADRAO,
			DIRECAO_PADRAO);

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

		repo = new VeiculoExploracaoRepositorioMemoriaImpl();
		repo.implantarVeiculoExploracao(sondaBase);

		sondaBase.setVeiculoExploracaoRepositorio(repoMock);
	}

	/**
	 * Realiza a obtenção com sucesso de uma sonda por seu identificador
	 * interno.
	 */
	@Test
	public void testObterVeiculoExploracaoPorIdentificador() {
		assertEquals(sondaBase,
				repo.obterVeiculoExploracaoPeloIdentificador("teste"));

	}

	/**
	 * Tenta obter uma sonda que ainda não foi implantada.
	 */
	@Test(expected = VeiculoExploracaoNaoEncontradoException.class)
	public void testObterVeiculoExploracaoNaoEncontradaPorIdentificador() {
		repo.obterVeiculoExploracaoPeloIdentificador("identificadorNaoExistente");

	}

	/**
	 * Realiza a implantação com sucesso de uma sonda.
	 */
	@Test
	public void testImplantarVeiculoExploracao() {
		SondaSimples novaSonda = new SondaSimples("novaSonda", new PosicaoCartesiana(2, 2),
				LESTE);

		assertEquals(novaSonda, repo.implantarVeiculoExploracao(novaSonda));

	}

	/**
	 * Tenta realizar a implantação de uma sonda que já foi implantada.
	 */
	@Test(expected = VeiculoExploracaoDuplicadoException.class)
	public void testImplantarSondaExistente() {
		repo.implantarVeiculoExploracao(sondaBase);

	}

	/**
	 * Tenta realizar a implantação de uma nova sonda sobre a mesma posição de
	 * outra já implantada.
	 */
	@Test(expected = ColisaoVeiculoExploracaoException.class)
	public void testImplantarVeiculoExploracaoColisaoPosicao() {
		repo.implantarVeiculoExploracao(new SondaSimples("novaSondaColisora",
				POSICAO_PADRAO, NORTE));

	}

	/**
	 * Atualiza com sucesso a posição de uma sonda implantada e que não colide
	 * com nenhum outra.
	 * 
	 * Neste caso, a direção continua a mesma.
	 */
	@Test
	public void testAtualizarPosicaoVeiculoExploracao() {

		SondaSimples sondaNovaPosicao = new SondaSimples(sondaBase.obterIdentificador(),
				new PosicaoCartesiana(2, 3), NORTE);

		repo.atualizarPosicaoDirecaoVeiculoExploracao(sondaNovaPosicao);

		assertEquals(sondaNovaPosicao,
				repo.obterVeiculoExploracaoPeloIdentificador(sondaNovaPosicao
						.obterIdentificador()));
	}

	/**
	 * Atualiza com sucesso a direção de uma sonda implantada e que não colide
	 * com nenhum outra.
	 * 
	 * Neste caso, a posição continua a mesma e apenas a direção é alterada.
	 */
	@Test
	public void testAtualizarApenasDirecaoVeiculoExploracao() {

		SondaSimples sondaNovaPosicao = new SondaSimples(sondaBase.obterIdentificador(),
				sondaBase.obterPosicaoAtual(), SUL);

		repo.atualizarPosicaoDirecaoVeiculoExploracao(sondaNovaPosicao);

		assertEquals(sondaNovaPosicao,
				repo.obterVeiculoExploracaoPeloIdentificador(sondaNovaPosicao
						.obterIdentificador()));
	}

	@Test(expected = ColisaoVeiculoExploracaoException.class)
	public void testAtualizarVeiculoExploracaoColisao() {

		/* Com o giro e movimentação a posição da sonda é alterada */
		sondaBase.girarParaDireita().movimentar();

		SondaSimples novaSontaColisora = (SondaSimples) repo
				.implantarVeiculoExploracao(new SondaSimples("novaSondaColisora",
						POSICAO_PADRAO, DIRECAO_PADRAO));
		novaSontaColisora.girarParaDireita().movimentar();

		repo.atualizarPosicaoDirecaoVeiculoExploracao(novaSontaColisora);

	}

	/**
	 * Tenta atualizar uma sonda que ainda não foi implantada.
	 */
	@Test(expected = VeiculoExploracaoNaoEncontradoException.class)
	public void testAtualizarVeiculoExploracaoNaoEncontrada() {

		SondaSimples sondaNaoImplantada = new SondaSimples("sondaNaoImplantada",
				POSICAO_PADRAO, DIRECAO_PADRAO);

		repo.atualizarPosicaoDirecaoVeiculoExploracao(sondaNaoImplantada);
	}

	/**
	 * Realiza a remoção de uma sonda já existente.
	 */
	@Test
	public void testRemoverVeiculoExploracaoExistente() {

		String identificadorSonda = sondaBase.obterIdentificador();
		repo.removerVeiculoExploracao(identificadorSonda);

		try {
			repo.obterVeiculoExploracaoPeloIdentificador(identificadorSonda);
			fail();
		} catch (VeiculoExploracaoNaoEncontradoException e) {
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
	public void testRemoverVeiculoExploracaoInexistente() {

		try {
			repo.removerVeiculoExploracao("identificadorInexistente");
		} catch (Exception e) {
			fail();
		}

	}

}
