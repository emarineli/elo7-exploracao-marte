/**
 * 
 */
package br.com.elo7.exploracao;

import java.util.UUID;

import org.springframework.util.Assert;

import br.com.elo7.exploracao.exeception.ImplantacaoException;
import br.com.elo7.exploracao.modelo.DirecaoCardeal;
import br.com.elo7.exploracao.modelo.PosicaoCartesiana;
import br.com.elo7.exploracao.modelo.Sonda;

/**
 * Fluent Interface que representa um objeto responsável por cuidar dos detalhes
 * de implantação de uma determinada sonda.
 * 
 * É possível realizar uma implantação por instância do implantador.
 * 
 * @author emarineli
 *
 */
public class CriadorVeiculoExploracao {

	private String identificadorVeiculo;
	private PosicaoCartesiana posicaoInicial;
	private DirecaoCardeal direcaoInicial;

	/**
	 * Indica qual será o identificador do veículo.
	 * 
	 * @param identificadorVeiculo
	 *            identificador do veículo de exploração.
	 * @return instância do implantador.
	 */
	public CriadorVeiculoExploracao comIdentificador(String identificadorVeiculo) {

		this.identificadorVeiculo = identificadorVeiculo;

		return this;
	}

	/**
	 * Disponibiliza um identificador do veículo baseado em UUID.
	 * 
	 * @return instância do implantador.
	 */
	public CriadorVeiculoExploracao comIdentificadorGerado() {

		this.identificadorVeiculo = UUID.randomUUID().toString();

		return this;
	}

	/**
	 * Indica qual a posição em que a sonda será implantada.
	 * 
	 * @param posicaoInicial
	 *            coordenada X-Y em um plano cartesiano onde a sonda deve ser
	 *            implantada.
	 * @return instância do implantador.
	 */
	public CriadorVeiculoExploracao naPosicao(PosicaoCartesiana posicaoInicial) {

		this.posicaoInicial = posicaoInicial;

		return this;
	}

	/**
	 * Indica que a coordenada X-Y inicial do veículo será a padrão.
	 * 
	 * @return
	 */
	public CriadorVeiculoExploracao naPosicaoPadrao() {
		this.posicaoInicial = PosicaoCartesiana.POSICAO_PADRAO;

		return this;
	}

	/**
	 * Indica qual a posição em que a sonda será implantada.
	 * 
	 * @param direcaoInicial
	 *            direção inicial seguindo a rosa dos ventos onde um veículo
	 *            pode ser implantado.
	 * @return instância do implantador.
	 */
	public CriadorVeiculoExploracao naDirecao(DirecaoCardeal direcaoInicial) {
		this.direcaoInicial = direcaoInicial;

		return this;
	}

	/**
	 * Indica que a coordenada X-Y inicial do veículo será a padrão.
	 * 
	 * @return
	 */
	public CriadorVeiculoExploracao naDirecaoPadrao() {
		this.direcaoInicial = DirecaoCardeal.DIRECAO_PADRAO;

		return this;
	}
	
	/**
	 * Realiza o processo de implantação de fato.
	 * 
	 * @throws ImplantacaoException
	 *             caso ocorra algum problema na implantação.
	 */
	public VeiculoExploracao criar() throws ImplantacaoException {

		Assert.hasText(identificadorVeiculo, "Um veículo deve ter um identificador único");
		Assert.notNull(direcaoInicial, "Uma Sonda necessita saber qual sua direção inicial!");
		Assert.notNull(posicaoInicial, "Uma Sonda necessita saber qual sua posição inicial!");

		return new Sonda(identificadorVeiculo, this.posicaoInicial, this.direcaoInicial);
		
	}

}
