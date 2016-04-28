package br.com.elo7.exploracao.modelo;

import static br.com.elo7.exploracao.modelo.DirecaoCardealEnum.DIRECAO_PADRAO;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.POSICAO_PADRAO;
import static org.springframework.util.Assert.hasText;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.com.elo7.exploracao.modelo.comando.ComandoVeiculoExploracao;
import br.com.elo7.exploracao.repositorio.VeiculoExploracaoRepositorio;

/**
 * Representa qualquer veículo de exploração.
 * 
 * @author emarineli
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = SondaSimples.class, name = "sondaSimples") })
@Configurable
public abstract class VeiculoExploracao {

	@JsonProperty
	private String identificadorVeiculoExploracao;

	@JsonProperty
	private PosicaoCartesiana posicaoAtual;

	@JsonProperty
	private DirecaoCardealEnum direcaoAtual;

	@Autowired
	private VeiculoExploracaoRepositorio veiculoExploracaoRepositorio;

	public VeiculoExploracao(String identificadorVeiculoExploracao, PosicaoCartesiana posicaoInicial,
			DirecaoCardealEnum direcaoInicial) {

		hasText(identificadorVeiculoExploracao, "O Veiculo de Exploracao deve possuir um identificador!");

		this.identificadorVeiculoExploracao = identificadorVeiculoExploracao;
		this.posicaoAtual = posicaoInicial == null ? POSICAO_PADRAO : posicaoInicial;
		this.direcaoAtual = direcaoInicial == null ? DIRECAO_PADRAO : direcaoInicial;
	}

	public String obterIdentificador() {
		return this.identificadorVeiculoExploracao;
	}

	public PosicaoCartesiana obterPosicaoAtual() {
		return this.posicaoAtual;
	}

	public void ajustarPosicaoAtual(PosicaoCartesiana novaPosicao) {
		this.posicaoAtual = novaPosicao;
	}

	public DirecaoCardealEnum obterDirecaoAtual() {
		return this.direcaoAtual;
	}

	public void ajustarDirecaoAtual(DirecaoCardealEnum novaDirecao) {
		this.direcaoAtual = novaDirecao;
	}

	public VeiculoExploracao processarComando(ComandoVeiculoExploracao comando) {

		comando.execute(this);

		this.veiculoExploracaoRepositorio.atualizarPosicaoDirecaoVeiculoExploracao(this);

		return this;

	}

	public void processarComandos(ComandoVeiculoExploracao... comandos) {
		for (ComandoVeiculoExploracao comando : comandos) {

			comando.execute(this);
			
			/* Atualização é feita por comando */
			this.veiculoExploracaoRepositorio.atualizarPosicaoDirecaoVeiculoExploracao(this);
		}

	}

	/**
	 * Obtém das classes filhas qual é o avanço padrão de um veículo.
	 * 
	 * @return inteiro positivo que indica o valor de avanço nos eixos X e Y.
	 */
	public abstract int obterAvancoPadrao();

	/**
	 * Visibilidade de dependência
	 * 
	 * @param sondaRepositorio
	 */
	public void setVeiculoExploracaoRepositorio(VeiculoExploracaoRepositorio sondaRepositorio) {
		this.veiculoExploracaoRepositorio = sondaRepositorio;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.identificadorVeiculoExploracao).append(this.posicaoAtual)
				.append(this.direcaoAtual).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof VeiculoExploracao) {
			final VeiculoExploracao other = (VeiculoExploracao) obj;

			return new EqualsBuilder().append(this.identificadorVeiculoExploracao, other.identificadorVeiculoExploracao)
					.append(this.posicaoAtual, other.posicaoAtual).append(this.direcaoAtual, other.direcaoAtual)
					.isEquals();

		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(this.identificadorVeiculoExploracao).append(this.posicaoAtual)
				.append(this.direcaoAtual).toString();
	}
}
