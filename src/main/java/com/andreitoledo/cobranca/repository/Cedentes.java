package com.andreitoledo.cobranca.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.andreitoledo.cobranca.model.Cedente;
import javax.persistence.PersistenceContext;

public class Cedentes implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "boletoCobrancaPU")
    private EntityManager manager;

    public Cedente guardar(Cedente cedente) {
        return manager.merge(cedente);
    }

    public Cedente porCodigo(Long codigo) {
        System.out.println("Cedente porCodigo:" + codigo);

        return this.manager.find(Cedente.class, codigo);
    }

}
