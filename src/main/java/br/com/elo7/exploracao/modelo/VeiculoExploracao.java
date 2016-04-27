package br.com.elo7.exploracao.modelo;

import static br.com.elo7.exploracao.modelo.DirecaoCardealEnum.DIRECAO_PADRAO;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.POSICAO_PADRAO;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.EixoCartesiano.X;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.EixoCartesiano.Y;
import static org.springframework.util.Assert.hasText;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import br.com.elo7.exploracao.modelo.comando.ComandoVeiculoExploracao;
import br.com.elo7.exploracao.repositorio.VeiculoExploracaoRepositorio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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

	public VeiculoExploracao(String identificadorVeiculoExploracao,
			PosicaoCartesiana posicaoInicial, DirecaoCardealEnum direcaoInicial) {

		hasText(identificadorVeiculoExploracao,
				"O Veiculo de Exploracao deve possuir um identificador!");

		this.identificadorVeiculoExploracao = identificadorVeiculoExploracao;
		this.posicaoAtual = posicaoInicial == null ? POSICAO_PADRAO
				: posicaoInicial;
		this.direcaoAtual = direcaoInicial == null ? DIRECAO_PADRAO
				: direcaoInicial;
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

		comando.execute();

		this.veiculoExploracaoRepositorio
				.atualizarPosicaoDirecaoVeiculoExploracao(this);

		return this;

	}

	public void processarComandos(ComandoVeiculoExploracao[] comandos) {
		for (ComandoVeiculoExploracao comando : comandos) {

			comando.execute();

			/* Atualização é feita por comando */
			this.veiculoExploracaoRepositorio
					.atualizarPosicaoDirecaoVeiculoExploracao(this);

		}
	}

	/**
	 * Realiza a movimentação do veículo.
	 * 
	 * A direção em que o veículo irá se movimentar depende de sua orientação da
	 * direção cardeal atual;
	 * 
	 * @return instância do veículo.
	 */
	public VeiculoExploracao movimentar() {

		/* O eixo da movimentação irá depender da direção atual */
		switch (this.direcaoAtual) {

		case NORTE:
			this.posicaoAtual = this.posicaoAtual.avancarNoEixo(Y,
					obterAvancoPadrao());
			break;

		case SUL:
			this.posicaoAtual = this.posicaoAtual.retrocederNoEixo(Y,
					obterAvancoPadrao());
			break;

		case LESTE:
			this.posicaoAtual = this.posicaoAtual.avancarNoEixo(X,
					obterAvancoPadrao());
			break;

		case OESTE:
			this.posicaoAtual = this.posicaoAtual.retrocederNoEixo(X,
					obterAvancoPadrao());
			break;
		}

		this.veiculoExploracaoRepositorio
				.atualizarPosicaoDirecaoVeiculoExploracao(this);

		return this;
	}

	/**
	 * Obtém das classes filhas qual é o avanço padrão de um veículo.
	 * 
	 * @return inteiro positivo que indica o valor de avanço nos eixos X e Y.
	 */
	public abstract int obterAvancoPadrao();

	/**
	 * Realiza um giro de 90 graus de sua direão cardeal para a esquerda.
	 * 
	 * @return instância do veículo.
	 */
	public VeiculoExploracao girarParaEquerda() {
		this.direcaoAtual = this.direcaoAtual.obterProximaDirecaoEsquerda();

		this.veiculoExploracaoRepositorio
				.atualizarPosicaoDirecaoVeiculoExploracao(this);

		return this;
	}

	/**
	 * Realiza um giro de 90 graus de sua direção cardeal para a direita.
	 * 
	 * @return instância do veículo.
	 */
	public VeiculoExploracao girarParaDireita() {
		this.direcaoAtual = this.direcaoAtual.obterProximaDirecaoDireita();

		this.veiculoExploracaoRepositorio
				.atualizarPosicaoDirecaoVeiculoExploracao(this);

		return this;
	}

	/**
	 * Visibilidade de dependência
	 * 
	 * @param sondaRepositorio
	 */
	public void setVeiculoExploracaoRepositorio(
			VeiculoExploracaoRepositorio sondaRepositorio) {
		this.veiculoExploracaoRepositorio = sondaRepositorio;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.identificadorVeiculoExploracao)
				.append(this.posicaoAtual).append(this.direcaoAtual)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof VeiculoExploracao) {
			final VeiculoExploracao other = (VeiculoExploracao) obj;

			return new EqualsBuilder()
					.append(this.identificadorVeiculoExploracao,
							other.identificadorVeiculoExploracao)
					.append(this.posicaoAtual, other.posicaoAtual)
					.append(this.direcaoAtual, other.direcaoAtual).isEquals();

		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(this.identificadorVeiculoExploracao)
				.append(this.posicaoAtual).append(this.direcaoAtual).toString();
	}
}
