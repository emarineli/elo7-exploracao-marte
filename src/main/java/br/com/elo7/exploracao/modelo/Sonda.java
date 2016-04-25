package br.com.elo7.exploracao.modelo;

import static br.com.elo7.exploracao.modelo.DirecaoCardeal.DIRECAO_PADRAO;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.POSICAO_PADRAO;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.EixoCartesiano.X;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.EixoCartesiano.Y;
import static org.springframework.util.Assert.hasText;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import br.com.elo7.exploracao.VeiculoExploracao;
import br.com.elo7.exploracao.repositorio.SondaRepositorio;

/**
 * Representa um veículo de exploração espacial que no caso é um veículo de
 * Sonda.
 * 
 * @author emarineli
 *
 */
@JsonRootName(value = "sonda")
@Configurable
public class Sonda implements VeiculoExploracao {

	private static final int AVANCO_PADRAO = 1;

	@JsonProperty
	private String identificadorSonda;

	@JsonProperty
	private PosicaoCartesiana posicaoAtual;

	@JsonProperty
	private DirecaoCardeal direcaoAtual;
	
	@Autowired
	private SondaRepositorio sondaRepositorio;

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
	public Sonda(@JsonProperty("identificadorSonda") String identificadorSonda,
			@JsonProperty("posicaoAtual") PosicaoCartesiana posicaoInicial,
			@JsonProperty("direcaoAtual") DirecaoCardeal direcaoInicial) {

		hasText(identificadorSonda, "A Sonda deve possuir um identificador!");

		this.identificadorSonda = identificadorSonda;
		this.posicaoAtual = posicaoInicial == null ? POSICAO_PADRAO : posicaoInicial;
		this.direcaoAtual = direcaoInicial == null ? DIRECAO_PADRAO : direcaoInicial;
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

		sondaRepositorio.atualizarPosicaoDirecaoSonda(this);
		
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sonda girarParaEquerda() {
		this.direcaoAtual = this.direcaoAtual.obterProximaDirecaoEsquerda();

		sondaRepositorio.atualizarPosicaoDirecaoSonda(this);
		
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sonda girarParaDireita() {
		this.direcaoAtual = this.direcaoAtual.obterProximaDirecaoDireita();

		sondaRepositorio.atualizarPosicaoDirecaoSonda(this);
		
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
