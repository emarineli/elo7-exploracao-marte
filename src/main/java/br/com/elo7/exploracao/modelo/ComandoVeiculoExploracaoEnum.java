package br.com.elo7.exploracao.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.elo7.exploracao.infraestrutura.serializer.ComandoVeiculoExploracaoTypeDeserializer;
import br.com.elo7.exploracao.modelo.comando.ComandoVeiculoExploracao;
import br.com.elo7.exploracao.modelo.comando.GirarVeiculoExploracaoDireitaComando;
import br.com.elo7.exploracao.modelo.comando.GirarVeiculoExploracaoEsquerdaComando;
import br.com.elo7.exploracao.modelo.comando.MovimentarVeiculoExploracaoComando;

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
public enum ComandoVeiculoExploracaoEnum {

	GIRAR_ESQUERDA("E", new GirarVeiculoExploracaoEsquerdaComando()), GIRAR_DIREITA("D",
			new GirarVeiculoExploracaoDireitaComando()), MOVER("M", new MovimentarVeiculoExploracaoComando());

	private String representacaoString;
	private ComandoVeiculoExploracao comandoVeiculo;

	private ComandoVeiculoExploracaoEnum(String representacaoString, ComandoVeiculoExploracao comandoVeiculo) {
		this.representacaoString = representacaoString;
		this.comandoVeiculo = comandoVeiculo;

	}

	public String obterRepresentacaoString() {
		return this.representacaoString;
	}

	public ComandoVeiculoExploracao obterComandoVeiculo() {
		return this.comandoVeiculo;
	}

	/**
	 * Obtém um comando por sua representação em string.
	 * 
	 * @param representacaoString
	 *            representação em string do comando.
	 * @return comando caso tenha sido encontrado.
	 */
	public static ComandoVeiculoExploracaoEnum obterDirecaoPorRepresentacao(String representacaoString) {

		for (ComandoVeiculoExploracaoEnum comando : values()) {
			if (comando.obterRepresentacaoString().equals(representacaoString)) {
				return comando;
			}
		}

		return null;
	}

}
