package br.com.elo7.exploracao.modelo;

import org.springframework.util.Assert;

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

		Assert.hasText(identificadorSonda, "A Sonda deve possuir um identificador!");
		Assert.notNull(posicaoInicial, "A sonda necessita saber qual será sua posição inicial!");
		Assert.notNull(direcaoInicial, "A sonda necessita saber qual será sua direção inicial!");

		this.identificadorSonda = identificadorSonda;
	}

	public PosicaoCartesiana obtemPosicaoAtual() {
		return this.posicaoAtual;
	}

	public DirecaoCardeal obtemDirecaoAtual() {
		return this.direcaoAtual;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void movimentar(Coordenada[] coordenadasDirecao) {

	}

	public String getIdentificador() {
		return this.identificadorSonda;
	}

}
