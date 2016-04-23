package br.com.elo7.exploracao.exeception;

import org.springframework.util.Assert;

import br.com.elo7.exploracao.modelo.PosicaoCartesiana;

/**
 * Indica falha na implantação. A natureza desta falha indica que ela não pode
 * ser tratada.
 * 
 * @author emarineli
 *
 */
public class ImplantacaoException extends RuntimeException {

	/**
	 * Serial gerado.
	 */
	private static final long serialVersionUID = 5507131868939799569L;

	private PosicaoCartesiana posicao;

	/**
	 * Indica falha na implantaçã de uma Sonda.
	 * 
	 * @param posicao
	 *            posição na qual foi realizada a tentativa de implantação.
	 * @param identificadorSonda
	 *            identificador da sonda que houve a falha de implantação.
	 * @param mensagem
	 *            mensagem de erro.
	 */
	public ImplantacaoException(PosicaoCartesiana posicao, String identificadorSonda, String mensagem) {
		super(mensagem);

		Assert.notNull(posicao, "Uma posição de falha precisa ser indicada!");
		Assert.hasText(identificadorSonda, "Deve ser apresentada a identificação da sonda!");
		Assert.hasText(mensagem, "A mensagem de falha precisa ser indicada!");

		this.posicao = posicao;
	}

	public PosicaoCartesiana getPosicaoFalha() {
		return this.posicao;
	}
}
