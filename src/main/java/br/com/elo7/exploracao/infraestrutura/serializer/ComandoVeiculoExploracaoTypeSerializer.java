package br.com.elo7.exploracao.infraestrutura.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.elo7.exploracao.modelo.ComandoVeiculoExploracaoEnum;

/**
 * Serializaer que transforma o enumerador em uma representação simplificada de
 * um array de string.
 * 
 * @author emarineli
 *
 */
public class ComandoVeiculoExploracaoTypeSerializer extends JsonSerializer<ComandoVeiculoExploracaoEnum> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void serialize(ComandoVeiculoExploracaoEnum values, JsonGenerator generator, SerializerProvider serializers)
			throws IOException, JsonProcessingException {

		generator.writeString(values.obterRepresentacaoString());
	}

}
