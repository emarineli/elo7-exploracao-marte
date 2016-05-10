package br.com.elo7.exploracao.modelo;

import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.POSICAO_PADRAO;
import static org.springframework.util.Assert.isTrue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidade que representa um terreno.
 * 
 * Cada terreno representa um plano cartesiano em duas dimensões que possuem um
 * ponto mínimo e máximo, ambos representados como um ponto no plano.
 * 
 * O ponto mínimo tem os valores nos eixos X = 0 e Y = 0.
 * 
 * Será permitida a criação de apenas uma instância desta classe para
 * representar um terreno de exploração para todas as sondas.
 * 
 * @author talent.emarineli
 *
 */
public class TerrenoExploracao {

	@JsonProperty
	private PosicaoCartesiana extensao;

	/**
	 * Construtor padrão.
	 * 
	 * @param extensao
	 *            posicação cartesiana no plano de duas dimensões que representa
	 *            o ponto máximo da malha
	 */
	public TerrenoExploracao(int eixoX, int eixoY) {

		isTrue(eixoX > 0 || eixoY > 0,
				"A extensão do terreno não pode ser igual ao seu ponto catesiano mínimo (0,0)");

		this.extensao = new PosicaoCartesiana(eixoX, eixoY);
	}

	@JsonCreator
	public TerrenoExploracao(
			@JsonProperty("extensao") PosicaoCartesiana extensao) {

		isTrue(extensao.getEixoX() > 0 || extensao.getEixoY() > 0,
				"A extensão do terreno não pode ser igual ao seu ponto catesiano mínimo (0,0)");

		this.extensao = new PosicaoCartesiana(extensao.getEixoX(),
				extensao.getEixoY());
	}

	/**
	 * Verifica se uma posição cartesiana está contina dentro dos limites do
	 * terreno de exploração.
	 * 
	 * @param posicaoAtual
	 *            posição a ser testada.
	 * @return se apossição está contina nos limites do terreno.
	 */
	public boolean posicaoContida(PosicaoCartesiana posicaoAtual) {
		return posicaoAtual.getEixoY() > this.obterExtensao().getEixoY()
				|| posicaoAtual.getEixoX() > this.obterExtensao().getEixoX()
				|| posicaoAtual.getEixoY() < POSICAO_PADRAO.getEixoY()
				|| posicaoAtual.getEixoX() < POSICAO_PADRAO.getEixoX();
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
