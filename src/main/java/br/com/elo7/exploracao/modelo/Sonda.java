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
	}

	public String getIdentificador() {
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
	public void movimentar() {
		
		/* O eixo da movimentação irá depender da direção atual */
		switch (this.direcaoAtual) {

		case NORTE:
			this.posicaoAtual = this.posicaoAtual.avancarNoEixo(Y);
			break;

		case SUL:
			this.posicaoAtual = this.posicaoAtual.retrocederNoEixo(X);
			break;

		case LESTE:
			this.posicaoAtual = this.posicaoAtual.avancarNoEixo(X);
			break;

		case OESTE:
			this.posicaoAtual = this.posicaoAtual.retrocederNoEixo(X);
			break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void girarParaEquerda() {
		this.direcaoAtual = this.direcaoAtual.obterProximaDirecaoEsquerda();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void girarParaDireita() {
		this.direcaoAtual = this.direcaoAtual.obterProximaDirecaoDireita();
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
