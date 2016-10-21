package com.andreitoledo.cobranca.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.andreitoledo.cobranca.model.Cobranca;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

@Stateless
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
    public List<Cobranca> todos(){
        return this.manager.createQuery("select c from Cobranca c").getResultList();
    }
}
