package br.com.elo7.exploracao.modelo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;

/**
 * Representa duas coordenadas em um plano cartesiano de duas dimensões.
 * 
 * @author emarineli
 *
 */
public class PosicaoCartesiana {

	private int eixoX = 0, eixoY = 0;

	public static PosicaoCartesiana POSICAO_PADRAO = new PosicaoCartesiana(0, 0);

	/**
	 * Cria uma posição baseada nos valores do eixo X e Y
	 * 
	 * @param eixoX
	 *            eixo X
	 * @param eixoY
	 *            eixo Y
	 */
	public PosicaoCartesiana(int eixoX, int eixoY) {

		Assert.isTrue(eixoX >= 0 && eixoY >= 0, "As coordenadas dos eixos X-Y devem ser iguais ou maior que zero!");

		this.eixoX = eixoX;
		this.eixoY = eixoY;
	}

	public int getEixoX() {
		return eixoX;
	}

	public int getEixoY() {
		return eixoY;
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
