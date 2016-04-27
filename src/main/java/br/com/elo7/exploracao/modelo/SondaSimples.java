package br.com.elo7.exploracao.modelo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa um veículo de exploração espacial que no caso é um veículo de
 * Sonda.
 * 
 * @author emarineli
 *
 */
public class SondaSimples extends VeiculoExploracao {

	private static final int AVANCO_PADRAO = 1;

	/**
	 * Constroi uma sonda lhe atribuindo um identificador que deve ser único.
	 * 
	 * @param identificadorSonda
	 *            identificador único da sonda.
	 * 
	 * @param posicaoInicial
	 *            indica qual será a posição inicial da sonda.
	 * @param direcaoInicial
	 *            indica qual será a direção inicial da sonda.
	 */
	@JsonCreator
	public SondaSimples(@JsonProperty("identificadorVeiculoExploracao") String identificadorSonda,
			@JsonProperty("posicaoAtual") PosicaoCartesiana posicaoInicial,
			@JsonProperty("direcaoAtual") DirecaoCardealEnum direcaoInicial) {

		super(identificadorSonda, posicaoInicial, direcaoInicial);

	}

	/**
	 * O avanço para este tipo de sonda é fixo.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public int obterAvancoPadrao() {
		return AVANCO_PADRAO;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().appendSuper(super.hashCode()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof SondaSimples) {
			return new EqualsBuilder().appendSuper(super.equals(obj))
					.isEquals();

		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.toString();
	}

}
