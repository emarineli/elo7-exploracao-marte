package br.com.elo7.exploracao.modelo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.springframework.util.Assert.isTrue;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidade que representa um terreno.
 * 
 * Cada terreno representa um plano cartesiano em duas dimensões que possuem um
 * ponto mínimo e máximo, ambos representados como um ponto no plano.
 * 
 * O ponto mínimo tem os valores nos eixos X = 0 e Y = 0.
 * 
 * @author talent.emarineli
 *
 */
public class Terreno {

	@JsonProperty
	private PosicaoCartesiana extensao;

	/**
	 * Construtor padrão.
	 * 
	 * @param extensao
	 *            posicação cartesiana no plano de duas dimensões que representa
	 *            o ponto máximo da malha
	 */
	public Terreno(PosicaoCartesiana extensao) {

		isTrue(extensao.getEixoX() > 0 || extensao.getEixoY() > 0,
				"A extensão do terreno não pode ser igual ao seu ponto catesiano mínimo (0,0)");

		this.extensao = extensao;
	}

	/**
	 * Obtém a extensão de um terreno.
	 * 
	 * @return posicação cartesiana no plano de duas dimensões que representa o
	 *         ponto máximo da malha.
	 */
	public PosicaoCartesiana obterExtensao() {
		return this.extensao;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
