package br.com.elo7.exploracao.teste.api.integracao;

import static br.com.elo7.exploracao.modelo.ComandoVeiculoExploracaoEnum.GIRAR_ESQUERDA;
import static br.com.elo7.exploracao.modelo.ComandoVeiculoExploracaoEnum.MOVER;
import static br.com.elo7.exploracao.modelo.DirecaoCardealEnum.NORTE;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.*;

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

import br.com.elo7.exploracao.exception.VeiculoExploracaoNaoEncontradoException;
import br.com.elo7.exploracao.infraestrutura.ExploracaoMarteApplication;
import br.com.elo7.exploracao.infraestrutura.api.MensagemRetorno;
import br.com.elo7.exploracao.modelo.ComandoVeiculoExploracaoEnum;
import br.com.elo7.exploracao.modelo.DirecaoCardealEnum;
import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
import br.com.elo7.exploracao.modelo.SondaSimples;
import br.com.elo7.exploracao.modelo.TerrenoExploracao;
import br.com.elo7.exploracao.modelo.VeiculoExploracao;

/**
 * Classe de testes das instruções enviadas a um veículo de exploração.
 * 
 * @author emarineli
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExploracaoMarteApplication.class)
@WebIntegrationTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext
public class InstrucoesVeiculoExploracaoRestControllerWebIntegrationTest {

	private static final String URI_BASE_SONDA_API = "http://localhost:8181/exploracao/v1/sondas";
	private static final String URI_TERRENO_API = "http://localhost:8181/exploracao/v1/terreno";

	private SondaSimples sondaBase = new SondaSimples("teste", new PosicaoCartesiana(1, 2), NORTE);

	private TerrenoExploracao terrenoBase = new TerrenoExploracao(5, 5);
	
	/**
	 * Testa o envio de instruções para um sonda já existente e associado a um
	 * terreno de exploração.
	 *
	 * ENTRADA:
	 * 
	 * 1 2 N LMLMLMLMM
	 * 
	 * SAIDA:
	 * 
	 * 1 3 N
	 * 
	 */
	@Test
	public void testEnviarInstrucoesSondaExistente() {

		/* criar terreno */
		
		ResponseEntity<?> responseTerreno = new TestRestTemplate().postForEntity(URI_TERRENO_API, terrenoBase, Void.class);

		assertEquals(CREATED, responseTerreno.getStatusCode());
		assertEquals(null, responseTerreno.getBody());

		/* cria e implanta uma sonda */
		ResponseEntity<SondaSimples> responseSonda = new TestRestTemplate().postForEntity(URI_BASE_SONDA_API, sondaBase,
				SondaSimples.class);

		sondaBase.associarTerrenoExploracao(terrenoBase);

		assertEquals(CREATED, responseSonda.getStatusCode());
		assertEquals(sondaBase, responseSonda.getBody());
		
		ComandoVeiculoExploracaoEnum[] comandos = new ComandoVeiculoExploracaoEnum[] { GIRAR_ESQUERDA, MOVER,
				GIRAR_ESQUERDA, MOVER, GIRAR_ESQUERDA, MOVER, GIRAR_ESQUERDA, MOVER, MOVER };

		ResponseEntity<VeiculoExploracao> response = new TestRestTemplate().postForEntity(
				URI_BASE_SONDA_API + "/" + sondaBase.obterIdentificador() + "/instrucoes", comandos,
				VeiculoExploracao.class);

		SondaSimples novaSonda = new SondaSimples(sondaBase.obterIdentificador(), new PosicaoCartesiana(1, 3),
				DirecaoCardealEnum.NORTE);
		novaSonda.associarTerrenoExploracao(terrenoBase);

		assertEquals(MOVED_PERMANENTLY, response.getStatusCode());
		assertEquals(novaSonda, response.getBody());

	}

	/**
	 * Tenta enviar comandos para uma sonda que ainda não existe.
	 */
	@Test
	public void testEnviarInstrucoesSondaNaoExistente() {
		
		SondaSimples novaSonda = new SondaSimples("novaSonda", new PosicaoCartesiana(1, 1), DirecaoCardealEnum.LESTE);

		MensagemRetorno mensagemEsperada = new MensagemRetorno(
				String.format(VeiculoExploracaoNaoEncontradoException.MENSAGEM, novaSonda.obterIdentificador()));

		ComandoVeiculoExploracaoEnum[] comandos = new ComandoVeiculoExploracaoEnum[] { GIRAR_ESQUERDA };

		ResponseEntity<MensagemRetorno> response = new TestRestTemplate().postForEntity(
				URI_BASE_SONDA_API + "/" + novaSonda.obterIdentificador() + "/instrucoes", comandos,
				MensagemRetorno.class);
		
		assertEquals(NOT_FOUND, response.getStatusCode());
		assertEquals(mensagemEsperada, response.getBody());

	}

}
