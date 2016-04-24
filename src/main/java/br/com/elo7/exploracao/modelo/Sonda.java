package br.com.elo7.exploracao.modelo;

import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.EixoCartesiano.X;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.EixoCartesiano.Y;
import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.elo7.exploracao.VeiculoExploracao;

/**
 * Representa um veículo de exploração espacial que no caso é um veículo de
 * Sonda.
 * 
 * @author emarineli
 *
 */
public class Sonda implements VeiculoExploracao {

	private static final int AVANCO_PADRAO = 1;

	private String identificadorSonda;

	private PosicaoCartesiana posicaoAtual;
	private DirecaoCardeal direcaoAtual;

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
	public Sonda(String identificadorSonda, PosicaoCartesiana posicaoInicial, DirecaoCardeal direcaoInicial) {

		hasText(identificadorSonda, "A Sonda deve possuir um identificador!");
		notNull(posicaoInicial, "A sonda necessita saber qual será sua posição inicial!");
		notNull(direcaoInicial, "A sonda necessita saber qual será sua direção inicial!");

		this.identificadorSonda = identificadorSonda;
		this.posicaoAtual = posicaoInicial;
		this.direcaoAtual = direcaoInicial;
	}

	public String obterIdentificador() {
		return this.identificadorSonda;
	}

	public PosicaoCartesiana obterPosicaoAtual() {
		return this.posicaoAtual;
	}

	public DirecaoCardeal obterDirecaoAtual() {
		return this.direcaoAtual;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sonda movimentar() {

		/* O eixo da movimentação irá depender da direção atual */
		switch (this.direcaoAtual) {

		case NORTE:
			this.posicaoAtual = this.posicaoAtual.avancarNoEixo(Y, AVANCO_PADRAO);
			break;

		case SUL:
			this.posicaoAtual = this.posicaoAtual.retrocederNoEixo(Y, AVANCO_PADRAO);
			break;

		case LESTE:
			this.posicaoAtual = this.posicaoAtual.avancarNoEixo(X, AVANCO_PADRAO);
			break;

		case OESTE:
			this.posicaoAtual = this.posicaoAtual.retrocederNoEixo(X, AVANCO_PADRAO);
			break;
		}
		
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sonda girarParaEquerda() {
		this.direcaoAtual = this.direcaoAtual.obterProximaDirecaoEsquerda();
		
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sonda girarParaDireita() {
		this.direcaoAtual = this.direcaoAtual.obterProximaDirecaoDireita();
		
		return this;
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
