package br.com.elo7.exploracao.teste.api.integracao;

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

import br.com.elo7.exploracao.exeception.TerrenoExploracaoJaCriadoException;
import br.com.elo7.exploracao.exeception.TerrenoExploracaoNaoEncontradoException;
import br.com.elo7.exploracao.infraestrutura.ExploracaoMarteApplication;
import br.com.elo7.exploracao.infraestrutura.api.MensagemRetorno;
import br.com.elo7.exploracao.modelo.TerrenoExploracao;

/**
 * Classe de testes para a API de Terreno
 * 
 * @author emarineli
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExploracaoMarteApplication.class)
@WebIntegrationTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext
public class TerrenoExploracaoRestControllerWebIntegrationTest {

	private static final String URI_TERRENO_API = "http://localhost:8181/exploracao/v1/terreno";

	/**
	 * Testa a obtenção de um terreno que ainda não foi criado.
	 */
	@Test
	public void test_a_ObterTerrenoAindaNaoCriado() {

		MensagemRetorno mensagemEsperada = new MensagemRetorno(
				String.format(TerrenoExploracaoNaoEncontradoException.MENSAGEM));

		ResponseEntity<MensagemRetorno> response = new TestRestTemplate().getForEntity(URI_TERRENO_API,
				MensagemRetorno.class);

		assertEquals(NOT_FOUND, response.getStatusCode());
		assertEquals(mensagemEsperada, response.getBody());

	}

	/**
	 * Testa a criação de um terreno que ainda não havia sido criado.
	 */
	@Test
	public void test_b_CriarTerreno() {

		TerrenoExploracao terreno = new TerrenoExploracao(5, 5);

		ResponseEntity<?> response = new TestRestTemplate().postForEntity(URI_TERRENO_API, terreno, Void.class);

		assertEquals(CREATED, response.getStatusCode());
		assertEquals(null, response.getBody());

	}

	/**
	 * Testa a criação de um terreno que já havia sido criado previamente.
	 */
	@Test
	public void test_c_CriarTerrenoJaExistente() {

		MensagemRetorno mensagemEsperada = new MensagemRetorno(
				String.format(TerrenoExploracaoJaCriadoException.MENSAGEM, 5, 5));

		TerrenoExploracao terreno = new TerrenoExploracao(6, 6);

		ResponseEntity<?> response = new TestRestTemplate().postForEntity(URI_TERRENO_API, terreno,
				MensagemRetorno.class);
		assertEquals(BAD_REQUEST, response.getStatusCode());
		assertEquals(mensagemEsperada, response.getBody());

	}

	/**
	 * Testa a obtenção de um terreno já criado.
	 */
	@Test
	public void test_d_ObterTerrenoExploracaoJaCriado() {

		TerrenoExploracao terreno = new TerrenoExploracao(5, 5);

		ResponseEntity<TerrenoExploracao> response = new TestRestTemplate().getForEntity(URI_TERRENO_API,
				TerrenoExploracao.class);

		assertEquals(OK, response.getStatusCode());
		assertEquals(terreno, response.getBody());
	}

}
