package br.com.elo7.exploracao.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.exploracao.modelo.ComandoVeiculoExploracaoEnum;
import br.com.elo7.exploracao.modelo.VeiculoExploracao;
import br.com.elo7.exploracao.repositorio.VeiculoExploracaoRepositorio;

/**
 * Recurso que define as instruções de comando das Sondas.
 * 
 * @author emarineli
 *
 */
@RestController
@RequestMapping("/sondas/{identificadorSonda}")
public class InstrucoesVeiculoExploracaoRestController {

	@Autowired
	private VeiculoExploracaoRepositorio sondaRepositorio;

	/**
	 * Operação responsável por enviar comandos para sonda.
	 * 
	 * @param identificadorSonda
	 *            identificador interno da sonda.
	 * @return
	 */
	@RequestMapping(produces = { APPLICATION_JSON_VALUE }, value = "/instrucoes", method = POST)
	public final ResponseEntity<VeiculoExploracao> comandarSonda(@PathVariable String identificadorSonda,
			@RequestBody ComandoVeiculoExploracaoEnum[] comandosSonda) {

		VeiculoExploracao sonda = sondaRepositorio.obterVeiculoExploracaoPeloIdentificador(identificadorSonda);

		for (ComandoVeiculoExploracaoEnum comando : comandosSonda) {
			sonda.processarComando(comando.obterComandoVeiculo());
		}
		
		sonda.add(linkTo(methodOn(VeiculoExploracaoRestController.class)
				.obterVeiculoExploracaoImplantada(sonda.obterIdentificador())).withRel("sondas"));

		return new ResponseEntity<VeiculoExploracao>(sonda, MOVED_PERMANENTLY);
	}

}
