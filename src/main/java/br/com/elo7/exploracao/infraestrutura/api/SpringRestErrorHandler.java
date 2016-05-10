package br.com.elo7.exploracao.infraestrutura.api;

import org.springframework.http.HttpHeaders;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.elo7.exploracao.exception.ColisaoVeiculoExploracaoException;
import br.com.elo7.exploracao.exception.TerrenoExploracaoJaCriadoException;
import br.com.elo7.exploracao.exception.TerrenoExploracaoNaoEncontradoException;
import br.com.elo7.exploracao.exception.VeiculoExploracaoDuplicadoException;
import br.com.elo7.exploracao.exception.VeiculoExploracaoNaoEncontradoException;

/**
 * Handler para o tratamento dos principais erros sistêmicos.
 * 
 * É assumido que o tipo de conteúdo do retorno será sempre JSON
 * (application/json).
 * 
 * @author emarineli
 *
 */
@ControllerAdvice
public class SpringRestErrorHandler {

	private static final String CONTENT_TYPE = "Content-Type";
	private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json; charset=utf-8";

	/**
	 * Responsável pelo tratamento de quaisquer outros tipos de erros não
	 * explicitamente identificado e que estarão sobre o código HTTP 500.
	 * 
	 * @param ex
	 *            exceção.
	 * @return entidade com a mensagem de erro.
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<MensagemRetorno> handlerException(final Exception ex) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
		return new ResponseEntity<MensagemRetorno>(
				criarMensagemErro(ex.getMessage() != null ? ex.getMessage()
						: "Erro desconhecido."), responseHeaders,
				INTERNAL_SERVER_ERROR);
	}

	/**
	 * Responsável por tratamento de erros do tipo 400 (BAD REQUEST). Todas as
	 * exceções que podem estar sobre este código HTTP devem ser explicitamente
	 * inseridas aqui.
	 * 
	 * @param ex
	 *            exceção.
	 * @return entidade com a mensagem de erro.
	 */
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class,
			IllegalArgumentException.class, JsonProcessingException.class,
			JsonMappingException.class,
			ColisaoVeiculoExploracaoException.class,
			VeiculoExploracaoDuplicadoException.class,
			TerrenoExploracaoJaCriadoException.class })
	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<MensagemRetorno> handlerBadRequest(final Exception ex) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
		return new ResponseEntity<MensagemRetorno>(
				criarMensagemErro(ex.getMessage()), responseHeaders,
				BAD_REQUEST);

	}

	/**
	 * Responsável pelo tratamento específico de código HTTP 404 - Not Found.
	 * 
	 * @param ex
	 *            exceção.
	 * @return entidade com a mensagem de erro.
	 */
	@ExceptionHandler({ VeiculoExploracaoNaoEncontradoException.class,
			TerrenoExploracaoNaoEncontradoException.class })
	@ResponseStatus(NOT_FOUND)
	@ResponseBody
	public ResponseEntity<MensagemRetorno> handlerNotFoundException(
			final Exception ex) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
		return new ResponseEntity<MensagemRetorno>(
				criarMensagemErro(ex.getMessage()), responseHeaders, NOT_FOUND);
	}

	private MensagemRetorno criarMensagemErro(String mensagemErro) {
		return new MensagemRetorno(mensagemErro);
	}

}
