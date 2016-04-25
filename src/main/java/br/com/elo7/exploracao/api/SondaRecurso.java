package br.com.elo7.exploracao.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.elo7.exploracao.modelo.Sonda;
import br.com.elo7.exploracao.repositorio.SondaRepositorio;

/**
 * API responsável pela manutenção de sondas.
 * 
 * @author emarineli
 *
 */
@Controller
@RequestMapping("/sondas")
public class SondaRecurso {

	@Autowired
	private SondaRepositorio sondaRepositorio;

	/**
	 * Operação responsável por criar e implantar uma sonda.
	 * 
	 * @param sonda
	 *            sonda a ser implantada.
	 * @return sonda implantada.
	 */
	@RequestMapping(consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE }, method = POST)
	public final @ResponseBody Sonda implantarSonda(@RequestBody Sonda sonda) {

		return sondaRepositorio.implantarSonda(sonda);
	}

	/**
	 * Operação responsável por obter uma sonda pelo seu identificador interno.
	 * 
	 * @param identificadorSonda
	 *            identificador da sonda.
	 * @return representação da sonda.
	 */
	@RequestMapping(produces = { APPLICATION_JSON_VALUE }, value = "/{identificadorSonda}", method = GET)
	public final @ResponseBody Sonda obterSondaImplantada(@PathVariable String identificadorSonda) {

		return sondaRepositorio.obterSondaPeloIdentificador(identificadorSonda);
	}

	/**
	 * Operação responsável por obter uma sonda pelo seu identificador interno.
	 * 
	 * @param identificadorSonda
	 *            identificador da sonda.
	 * @return representação da sonda.
	 */
	@RequestMapping(produces = { APPLICATION_JSON_VALUE }, value = "/{identificadorSonda}", method = DELETE)
	public final @ResponseBody void removerSondaImplantada(@PathVariable String identificadorSonda) {
		sondaRepositorio.removerSonda(identificadorSonda);
	}
}
