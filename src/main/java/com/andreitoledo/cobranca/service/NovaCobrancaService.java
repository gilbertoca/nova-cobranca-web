package com.andreitoledo.cobranca.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.andreitoledo.cobranca.model.Cobranca;
import com.andreitoledo.cobranca.model.Status;
import com.andreitoledo.cobranca.repository.Cobrancas;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class NovaCobrancaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cobrancas cobrancas;

	@Transactional
	public Cobranca salvar(Cobranca cobranca) {
		cobranca.setStatus(Status.PENDENTE);
		cobranca = this.cobrancas.guardar(cobranca);

		return cobranca;
	}

}
