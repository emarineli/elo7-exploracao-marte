package br.com.elo7.exploracao.modelo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;

import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.EixoCartesiano.*;

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

	/**
	 * Realiza o avanço em uma unidade de posição no eixo indicado
	 * 
	 * @param eixo
	 *            eixo de avanço
	 * @param avanco
	 *            indica a quantidade do avanço no eixo indicado.
	 * 
	 * @return nova instância da Posicao Cartesiana com avanço realizado.
	 */
	public PosicaoCartesiana avancarNoEixo(EixoCartesiano eixo, int avanco) {

		return (eixo == X ? new PosicaoCartesiana(this.eixoX + avanco, this.eixoY)
				: new PosicaoCartesiana(this.eixoX, this.eixoY + avanco));
	}

	/**
	 * Realiza o retrocesso em uma unidade de posição no eixo indicado
	 * 
	 * @param eixo
	 *            eixo de avanço
	 * @param avanco
	 *            indica a quantidade do avanço no eixo indicado.
	 * 
	 * @return nova instância da Posicao Cartesiana com retrocesso realizado.
	 */
	public PosicaoCartesiana retrocederNoEixo(EixoCartesiano eixo, int avanco) {

		if(this.eixoX < 0 || this.eixoY < 0) {
			return this;
		}
		
		return (eixo == X ? new PosicaoCartesiana(this.eixoX - avanco, this.eixoY)
				: new PosicaoCartesiana(this.eixoX, this.eixoY - avanco));
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

	/**
	 * Enumerador que representa os eixos cartesianos em um plano de duas
	 * dimensões.
	 * 
	 * @author emarineli
	 *
	 */
	public enum EixoCartesiano {
		Y, X
	}
}
