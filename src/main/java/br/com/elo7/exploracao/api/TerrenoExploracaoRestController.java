package br.com.elo7.exploracao.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.exploracao.modelo.TerrenoExploracao;
import br.com.elo7.exploracao.repositorio.TerrenoExploracaoRepositorio;

/**
 * Representa as operações do recurso do Terreno.
 * 
 * @author talent.emarineli
 *
 */
@RestController
@RequestMapping("/terreno")
public class TerrenoExploracaoRestController {

	@Autowired
	private TerrenoExploracaoRepositorio terrenoExploracaoRepositorio;

	/**
	 * Operação responsável por criar um terreno utilizado pela exploração da
	 * sonda.
	 * 
	 * @param sonda
	 *            sonda a ser implantada.
	 * @return sonda implantada.
	 */
	@RequestMapping(consumes = { APPLICATION_JSON_VALUE }, method = POST)
	public final ResponseEntity<?> criarTerrenoExploracao(
			@RequestBody TerrenoExploracao terreno) {

		terrenoExploracaoRepositorio.criarTerrenoExploracao(terreno);

		return new ResponseEntity<>(CREATED);
	}

	/**
	 * Operação responsável por obter uma sonda pelo seu identificador interno.
	 * 
	 * @param identificadorVeiculoExploracao
	 *            identificador da sonda.
	 * @return representação da sonda.
	 */
	@RequestMapping(produces = { APPLICATION_JSON_VALUE }, method = GET)
	public final @ResponseBody TerrenoExploracao obterTerrenoExploracao() {

		return terrenoExploracaoRepositorio.obterTerrenoExploracao();
	}

}
