package br.com.elo7.exploracao;

import static br.com.elo7.exploracao.modelo.DirecaoCardeal.DIRECAO_PADRAO;
import static br.com.elo7.exploracao.modelo.PosicaoCartesiana.POSICAO_PADRAO;

import java.util.UUID;

import org.springframework.util.Assert;

import br.com.elo7.exploracao.modelo.DirecaoCardeal;
import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
import br.com.elo7.exploracao.modelo.Sonda;

/**
 * Builder responsável por auxiliar a construção de uma sonda.
 * 
 * @author emarineli
 *
 */
public class SondaBuilder {

	private PosicaoCartesiana posicaoInicial;
	private DirecaoCardeal direcaoInicial;
	private String identificadorSonda;

	public SondaBuilder naPosicaoInicial(int eixoX, int eixoY) {

		this.posicaoInicial = new PosicaoCartesiana(eixoX, eixoY);

		return this;
	}

	/**
	 * Cria uma posição inicial baseada na padrão, onde os eixos X-Y são 0
	 * (zero).
	 * 
	 * @return instância do builder.
	 */
	public SondaBuilder naPosicaoPadraoInicial() {

		this.posicaoInicial = POSICAO_PADRAO;

		return this;
	}

	public SondaBuilder naDirecaoInicial(DirecaoCardeal direcaoInicial) {

		this.direcaoInicial = direcaoInicial;

		return this;
	}

	/**
	 * Cria uma direção inicial baseada na padrão que aponta para NORTE.
	 * 
	 * @return instância do builder.
	 */
	public SondaBuilder naDirecaoPadraoInicial() {

		this.direcaoInicial = DIRECAO_PADRAO;

		return this;
	}

	public SondaBuilder comIdentificador(String identificadorSonda) {

		this.identificadorSonda = identificadorSonda;

		return this;
	}

	/**
	 * Gera o identificador da sonda baseado em um UUID randômico.
	 * 
	 * @return instância do builder.
	 */
	public SondaBuilder comIdentificadorUUIDGerado() {

		this.identificadorSonda = UUID.randomUUID().toString();

		return this;
	}

	public Sonda criarSonda() {

		Assert.hasText(this.identificadorSonda, "A Sonda deve possuir um identificador!");
		Assert.notNull(this.direcaoInicial, "Uma direção inicial da Sonda deve ser informada!");
		Assert.notNull(this.posicaoInicial, "Uma posição inicial da Sonda deve ser informada!");

		return new Sonda(this.identificadorSonda, this.posicaoInicial, this.direcaoInicial);

	}

}
