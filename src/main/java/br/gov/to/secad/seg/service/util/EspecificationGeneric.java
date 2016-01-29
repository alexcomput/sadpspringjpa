/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.service.util;
 
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification; 

/**
 *
 * @author alex.santos
 */
public abstract class EspecificationGeneric {

    public Specification<Object> especificationAvaliacaoGeneric(final Object object, final String tipo, final String fieldCampo) {
        
        return new Specification<Object>() {
            @Override
            public Predicate toPredicate(Root<Object> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                /*
                 Confere se o objeto é diferente de NULL
                 */
                if (object != null) {
                    /*
                     Depois confere se o mesmo é DO TIPO INTEIRO
                     */
                    if (tipo.equals("I") && Integer.getInteger(object.toString()) > 0) {
                        return cb.equal(root.get(fieldCampo), object);

                    } else if (!object.equals("")) {
                        /*
                         Depois confere se o objeto é diferente de vazio
                         */
                        return cb.equal(root.get(fieldCampo), object);
                    }
                }
                return null;
            }
        };
    }
}
