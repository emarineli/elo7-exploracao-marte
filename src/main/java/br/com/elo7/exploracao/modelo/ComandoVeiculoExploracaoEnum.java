package br.com.elo7.exploracao.modelo;

import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.elo7.exploracao.infraestrutura.serializer.ComandoVeiculoExploracaoTypeDeserializer;
import br.com.elo7.exploracao.infraestrutura.serializer.ComandoVeiculoExploracaoTypeSerializer;
import br.com.elo7.exploracao.modelo.comando.ComandoVeiculoExploracao;
import br.com.elo7.exploracao.modelo.comando.GirarVeiculoExploracao90GrausDireitaComando;
import br.com.elo7.exploracao.modelo.comando.GirarVeiculoExploracao90grausEsquerdaComando;
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
@JsonSerialize(using = ComandoVeiculoExploracaoTypeSerializer.class)
public enum ComandoVeiculoExploracaoEnum {

	GIRAR_ESQUERDA("E", new GirarVeiculoExploracao90grausEsquerdaComando()), GIRAR_DIREITA("D",
			new GirarVeiculoExploracao90GrausDireitaComando()), MOVER("M", new MovimentarVeiculoExploracaoComando());

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
	public static Optional<ComandoVeiculoExploracaoEnum> obterDirecaoPorRepresentacao(String representacaoString) {

		for (ComandoVeiculoExploracaoEnum comando : values()) {
			if (comando.obterRepresentacaoString().equals(representacaoString)) {
				return Optional.of(comando);
			}
		}

		return Optional.empty();
	}

}
