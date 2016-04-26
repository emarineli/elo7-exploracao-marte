package br.com.elo7.exploracao.infraestrutura.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.elo7.exploracao.modelo.ComandoSonda;

/**
 * Deserializador que transforma a representação simplificada em string de um
 * comando de sonda em um enumerador.
 * 
 * @author emarineli
 *
 */
public class ComandoSondaTypeDeserializer extends JsonDeserializer<ComandoSonda> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComandoSonda deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		ComandoSonda cs = ComandoSonda.obterDirecaoPorRepresentacao(p.getText());

		if (cs == null) {
			throw new IllegalArgumentException("A representação simplificada do comando de sonda [" + p.getText()
					+ "] é inválida." + "As representações válidas são: D,E,M");
		}

		return cs;
	}

}
