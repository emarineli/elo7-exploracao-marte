package br.com.elo7.exploracao.infraestrutura.api;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe responsável por estruturar a mensagem de erro que será enviada ao
 * solicitante do recuso.
 * 
 * @author emarineli
 *
 */
public class MensagemErro {

	@JsonProperty
	private String mensagem;

	public MensagemErro(String mensagem) {
		this.mensagem = mensagem;
	}

	public String obterMensagemErro() {
		return this.mensagem;
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
