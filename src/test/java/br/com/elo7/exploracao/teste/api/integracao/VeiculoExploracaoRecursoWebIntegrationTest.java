package br.com.elo7.exploracao.teste.api.integracao;

import static br.com.elo7.exploracao.modelo.DirecaoCardealEnum.DIRECAO_PADRAO;
import static br.com.elo7.exploracao.modelo.DirecaoCardealEnum.SUL;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.POSICAO_PADRAO;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.elo7.exploracao.exception.ColisaoVeiculoExploracaoException;
import br.com.elo7.exploracao.exception.VeiculoExploracaoDuplicadoException;
import br.com.elo7.exploracao.exception.VeiculoExploracaoNaoEncontradoException;
import br.com.elo7.exploracao.infraestrutura.ExploracaoMarteApplication;
import br.com.elo7.exploracao.infraestrutura.api.MensagemRetorno;
import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
import br.com.elo7.exploracao.modelo.SondaSimples;
import br.com.elo7.exploracao.modelo.TerrenoExploracao;

/**
 * Testes de integração da API de recusos de Sonda.
 * 
 * @author talent.emarineli
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExploracaoMarteApplication.class)
@WebIntegrationTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext
public class VeiculoExploracaoRecursoWebIntegrationTest {

	private static final String URI_SONDA_API = "http://localhost:8181/exploracao/v1/sondas";
	private static final String URI_TERRENO_API = "http://localhost:8181/exploracao/v1/terreno";

	private static final MensagemRetorno MENSAGEM_ERRO_IMPLANTACAO_TERRENO = new MensagemRetorno(
			"A sonda não pode ultrapassar o terreno de exploração associado para sua implantação.");

	private SondaSimples sondaBase = new SondaSimples("teste", POSICAO_PADRAO, DIRECAO_PADRAO);

	/**
	 * Realiza a implantação de uma sonda.
	 */
	@Test
	public void test_a_ImplantarSonda() {

		TerrenoExploracao terreno = new TerrenoExploracao(10, 10);

		new TestRestTemplate().postForEntity(URI_TERRENO_API, terreno, TerrenoExploracao.class);

		ResponseEntity<SondaSimples> response = criarSondaBase();
		sondaBase.associarTerrenoExploracao(terreno);

		assertEquals(CREATED, response.getStatusCode());
		assertEquals(sondaBase, response.getBody());

	}

	/**
	 * Realiza a implantação de uma sonda.
	 */
	@Test
	public void test_a_a_ImplantarSondaForaTerrenoExploracaoEixoX() {

		SondaSimples novaSonda = new SondaSimples("teste", new PosicaoCartesiana(11, 1), DIRECAO_PADRAO);

		ResponseEntity<MensagemRetorno> response = new TestRestTemplate().postForEntity(URI_SONDA_API, novaSonda,
				MensagemRetorno.class);

		assertEquals(BAD_REQUEST, response.getStatusCode());
		assertEquals(MENSAGEM_ERRO_IMPLANTACAO_TERRENO, response.getBody());

	}

	/**
	 * Realiza a implantação de uma sonda.
	 */
	@Test
	public void test_a_b_ImplantarSondaForaTerrenoExploracaoEixoX() {

		SondaSimples novaSonda = new SondaSimples("teste", new PosicaoCartesiana(1, 11), DIRECAO_PADRAO);

		ResponseEntity<MensagemRetorno> response = new TestRestTemplate().postForEntity(URI_SONDA_API, novaSonda,
				MensagemRetorno.class);

		assertEquals(BAD_REQUEST, response.getStatusCode());
		assertEquals(MENSAGEM_ERRO_IMPLANTACAO_TERRENO, response.getBody());

	}

	/**
	 * Realiza a implantação de uma sonda.
	 */
	@Test
	public void test_a_c_ImplantarSondaJaExistente() {

		MensagemRetorno mensagemEsperada = new MensagemRetorno(
				String.format(VeiculoExploracaoDuplicadoException.MENSAGEM, sondaBase.obterIdentificador()));

		ResponseEntity<MensagemRetorno> response = new TestRestTemplate().postForEntity(URI_SONDA_API, sondaBase,
				MensagemRetorno.class);

		assertEquals(BAD_REQUEST, response.getStatusCode());
		assertEquals(mensagemEsperada, response.getBody());

	}

	/**
	 * Realiza a implantação de uma sonda.
	 */
	@Test
	public void test_a_b_ImplantarSondaNaMesmaPosicao() {

		String identificadorNovaSonda = "novaSonda";

		MensagemRetorno mensagemEsperada = new MensagemRetorno(String.format(ColisaoVeiculoExploracaoException.MENSAGEM,
				identificadorNovaSonda, sondaBase.obterIdentificador()));

		ResponseEntity<MensagemRetorno> response = new TestRestTemplate().postForEntity(URI_SONDA_API,
				new SondaSimples(identificadorNovaSonda, sondaBase.obterPosicaoAtual(), SUL), MensagemRetorno.class);

		assertEquals(BAD_REQUEST, response.getStatusCode());
		assertEquals(mensagemEsperada, response.getBody());

	}

	/**
	 * Realiza a obtenção de uma sonda já implantada.
	 */
	@Test
	public void test_b_ObterSonda() {

		TerrenoExploracao terreno = new TerrenoExploracao(10, 10);

		ResponseEntity<SondaSimples> response = new TestRestTemplate().getForEntity(URI_SONDA_API + "/teste",
				SondaSimples.class);

		sondaBase.associarTerrenoExploracao(terreno);

		assertEquals(OK, response.getStatusCode());
		assertEquals(sondaBase, response.getBody());

	}

	/**
	 * Realiza a remoção de uma sonda.
	 */
	@Test
	public void test_c_RemoverSonda() {

		new TestRestTemplate().delete(URI_SONDA_API + "/teste");

	}

	/**
	 * Tenta obter uma sonda que não foi implantada.
	 */
	@Test
	public void test_d_ObterSondaNaoExistente() {

		String identificadorSonda = "identificadorInvalido";

		MensagemRetorno mensagemEsperada = new MensagemRetorno(
				String.format(VeiculoExploracaoNaoEncontradoException.MENSAGEM, identificadorSonda));

		ResponseEntity<MensagemRetorno> response = new TestRestTemplate()
				.getForEntity(URI_SONDA_API + "/" + identificadorSonda, MensagemRetorno.class);

		assertEquals(NOT_FOUND, response.getStatusCode());
		assertEquals(mensagemEsperada, response.getBody());

	}

	private ResponseEntity<SondaSimples> criarSondaBase() {

		return new TestRestTemplate().postForEntity(URI_SONDA_API, sondaBase, SondaSimples.class);

	}
}
