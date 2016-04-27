package br.com.elo7.exploracao.api;

import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.elo7.exploracao.modelo.ComandoVeiculoExploracao;
import br.com.elo7.exploracao.modelo.VeiculoExploracao;
import br.com.elo7.exploracao.repositorio.VeiculoExploracaoRepositorio;

/**
 * Recurso que define as instruções de comando das Sondas.
 * 
 * @author emarineli
 *
 */
@Controller
@RequestMapping("/sondas/{identificadorSonda}")
public class InstrucoesVeiculoExploracaoRecurso {

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
	public final ResponseEntity<VeiculoExploracao> comandarSonda(
			@PathVariable String identificadorSonda,
			@RequestBody ComandoVeiculoExploracao[] comandosSonda) {

		VeiculoExploracao sonda = sondaRepositorio
				.obterVeiculoExploracaoPeloIdentificador(identificadorSonda);

		for (ComandoVeiculoExploracao comando : comandosSonda) {
			switch (comando) {
			case GIRAR_ESQUERDA:
				sonda.girarParaEquerda();
				break;
			case GIRAR_DIREITA:
				sonda.girarParaDireita();
				break;

			case MOVER:
				sonda.movimentar();
				break;

			}
		}

		return new ResponseEntity<VeiculoExploracao>(sonda, MOVED_PERMANENTLY);
	}

}
