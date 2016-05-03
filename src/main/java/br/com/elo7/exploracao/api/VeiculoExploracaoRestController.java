package br.com.elo7.exploracao.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.exploracao.infraestrutura.api.MensagemRetorno;
import br.com.elo7.exploracao.modelo.TerrenoExploracao;
import br.com.elo7.exploracao.modelo.VeiculoExploracao;
import br.com.elo7.exploracao.repositorio.TerrenoExploracaoRepositorio;
import br.com.elo7.exploracao.repositorio.VeiculoExploracaoRepositorio;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * API responsável pela manutenção de sondas.
 * 
 * @author emarineli
 *
 */
@RestController
@RequestMapping("/sondas")
public class VeiculoExploracaoRestController {

	@Autowired
	private VeiculoExploracaoRepositorio sondaRepositorio;

	@Autowired
	private TerrenoExploracaoRepositorio terrenoExploracaoRepositorio;

	/**
	 * Operação responsável por criar e implantar uma sonda.
	 * 
	 * @param sonda
	 *            sonda a ser implantada.
	 * @return sonda implantada.
	 */
	@RequestMapping(consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE }, method = POST)
	public ResponseEntity<VeiculoExploracao> implantarVeiculoExploracao(@RequestBody VeiculoExploracao sonda) {

		/*
		 * Para cada sonda criada, um terreno de exploração deve ser associado.
		 */
		TerrenoExploracao terrenoExploracao = terrenoExploracaoRepositorio.obterTerrenoExploracao();
		sonda.associarTerrenoExploracao(terrenoExploracao);
		
		sonda = sondaRepositorio.implantarVeiculoExploracao(sonda);
		
		sonda.add(linkTo(methodOn(VeiculoExploracaoRestController.class)
				.removerVeiculoExploracaoImplantada(sonda.obterIdentificador())).withSelfRel());


		return new ResponseEntity<VeiculoExploracao>(sonda, CREATED);
	}

	/**
	 * Operação responsável por obter uma sonda pelo seu identificador interno.
	 * 
	 * @param identificadorVeiculoExploracao
	 *            identificador da sonda.
	 * @return representação da sonda.
	 */
	@RequestMapping(produces = { APPLICATION_JSON_VALUE }, value = "/{identificadorVeiculoExploracao}", method = GET)
	public @ResponseBody VeiculoExploracao obterVeiculoExploracaoImplantada(
			@PathVariable String identificadorVeiculoExploracao) {

		VeiculoExploracao ve = sondaRepositorio.obterVeiculoExploracaoPeloIdentificador(identificadorVeiculoExploracao);

		ve.add(linkTo(VeiculoExploracaoRestController.class).slash(ve.obterIdentificador()).withSelfRel());
		return ve;
	}

	/**
	 * Operação responsável por obter uma sonda pelo seu identificador interno.
	 * 
	 * Esta operação tem o intúito de ser idempotente. Mesmo que uma sonda não
	 * for encontrada para remoção o resultado final se mantém o mesmo.
	 * 
	 * @param identificadorVeiculoExploracao
	 *            identificador da sonda.
	 * @return representação da sonda.
	 */
	@RequestMapping(produces = { APPLICATION_JSON_VALUE }, value = "/{identificadorVeiculoExploracao}", method = DELETE)
	public ResponseEntity<MensagemRetorno> removerVeiculoExploracaoImplantada(
			@PathVariable String identificadorVeiculoExploracao) {

		sondaRepositorio.removerVeiculoExploracao(identificadorVeiculoExploracao);

		return new ResponseEntity<MensagemRetorno>(new MensagemRetorno("VeiculoExploracao removida"), OK);
	}
}
