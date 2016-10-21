package com.andreitoledo.cobranca.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.andreitoledo.cobranca.model.Cedente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

@Stateless
public class Cedentes implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "boletoCobrancaPU")
    private EntityManager manager;

    public Cedente guardar(Cedente cedente) {
        return manager.merge(cedente);
    }

    public Cedente porCodigo(Long codigo) {
        return this.manager.find(Cedente.class, codigo);
    }
    public List<Cedente> todos(){
        return this.manager.createQuery("select c from Cedente c").getResultList();
    }
}
