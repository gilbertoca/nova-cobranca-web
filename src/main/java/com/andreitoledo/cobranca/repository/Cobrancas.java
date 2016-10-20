package com.andreitoledo.cobranca.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.andreitoledo.cobranca.model.Cobranca;
import javax.persistence.PersistenceContext;

public class Cobrancas implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "boletoCobrancaPU")
    private EntityManager manager;

    public Cobranca guardar(Cobranca cobranca) {
        return manager.merge(cobranca);
    }

    public Cobranca porCodigo(Long codigoCobranca) {
        return this.manager.find(Cobranca.class, codigoCobranca);
    }

}
