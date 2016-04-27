package br.com.elo7.exploracao.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.elo7.exploracao.infraestrutura.serializer.ComandoVeiculoExploracaoTypeDeserializer;

/**
 * Enumerador que contém os possíveis comandos que um veículo de exploração pode
 * receber.
 * 
 * Um deserializador é necessário pois os comandos serão enviados pela
 * representação simplificada em string.
 * 
 * @author emarineli
 *
 */
@JsonDeserialize(using = ComandoVeiculoExploracaoTypeDeserializer.class)
public enum ComandoVeiculoExploracao {

	GIRAR_ESQUERDA("E"), GIRAR_DIREITA("D"), MOVER("M");

	private String representacaoString;

	private ComandoVeiculoExploracao(String representacaoString) {
		this.representacaoString = representacaoString;
	}

	public String obterRepresentacaoString() {
		return this.representacaoString;
	}

	/**
	 * Obtém um comando por sua representação em string.
	 * 
	 * @param representacaoString
	 *            representação em string do comando.
	 * @return comando caso tenha sido encontrado.
	 */
	public static ComandoVeiculoExploracao obterDirecaoPorRepresentacao(
			String representacaoString) {

		for (ComandoVeiculoExploracao comando : values()) {
			if (comando.obterRepresentacaoString().equals(representacaoString)) {
				return comando;
			}
		}

		return null;
	}

}
