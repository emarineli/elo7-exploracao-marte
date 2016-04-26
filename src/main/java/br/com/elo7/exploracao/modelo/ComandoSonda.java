package br.com.elo7.exploracao.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.elo7.exploracao.infraestrutura.serializer.ComandoSondaTypeDeserializer;

/**
 * Enumerador que contém os possíveis comandos que uma sonda pode receber.
 * 
 * @author emarineli
 *
 */
@JsonDeserialize(using = ComandoSondaTypeDeserializer.class)
public enum ComandoSonda {

	GIRAR_ESQUERDA("E"), GIRAR_DIREITA("D"), MOVER("M");

	private String representacaoString;

	private ComandoSonda(String representacaoString) {
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
	public static ComandoSonda obterDirecaoPorRepresentacao(String representacaoString) {

		for (ComandoSonda comando : values()) {
			if (comando.obterRepresentacaoString().equals(representacaoString)) {
				return comando;
			}
		}

		return null;
	}

}
