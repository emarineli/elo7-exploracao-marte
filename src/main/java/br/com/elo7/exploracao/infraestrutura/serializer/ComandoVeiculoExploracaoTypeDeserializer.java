package br.com.elo7.exploracao.infraestrutura.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.elo7.exploracao.modelo.ComandoVeiculoExploracaoEnum;

/**
 * Deserializador que transforma a representação simplificada em string de um
 * comando de sonda em um enumerador.
 * 
 * @author emarineli
 *
 */
public class ComandoVeiculoExploracaoTypeDeserializer extends
		JsonDeserializer<ComandoVeiculoExploracaoEnum> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComandoVeiculoExploracaoEnum deserialize(JsonParser p,
			DeserializationContext ctxt) throws IOException,
			JsonProcessingException {

		String direcaoString = p.getText();

		return ComandoVeiculoExploracaoEnum.obterDirecaoPorRepresentacao(
				direcaoString).orElseThrow(
				() -> new IllegalArgumentException(
						"A representação simplificada do comando de sonda ["
								+ direcaoString + "] é inválida."
								+ "As representações válidas são: D,E,M"));

	}

}
