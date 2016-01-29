/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.service;

import br.gov.to.secad.seg.domain.Avaliacao;
import br.gov.to.secad.seg.repository.IAvaliacaoRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.data.jpa.domain.Specifications.where;

/**
 *
 * @author alex.santos
 */
@Service
public class AvaliacaoService {

    @Autowired
    IAvaliacaoRepository iAvaliacaoRepository;

    @Transactional(timeout = 10)
    public List<Avaliacao> findByAvaliacao(String cpf, Integer idGrupoUsuario) {
        try {
            return iAvaliacaoRepository.findAll();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Transactional(timeout = 10)
    public List<Avaliacao> findByAvaliacaoMatricula(String matricula) {
        try {
            return iAvaliacaoRepository.findAll(where(especificationAvaliacaoGeneric(matricula, "S", "matricula")));
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Transactional(timeout = 10)
    public Avaliacao fndByEtapa(Integer idAvaliacao) {
        try {
            return (Avaliacao) iAvaliacaoRepository.findOne(where(especificationAvaliacaoGeneric(idAvaliacao, "S", "id")));
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Transactional(timeout = 10)
    public List<Avaliacao> findAll() {
        try {
            return iAvaliacaoRepository.findAll();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getLocalizedMessage());
            return null;
        }
    }

    public boolean salvar(Avaliacao menu) {
        try {
            iAvaliacaoRepository.save(menu);
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getLocalizedMessage());
        }
        return false;
    }

    public boolean removerAvaliacao(Avaliacao menu) {
        try {
            iAvaliacaoRepository.delete(menu);
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getLocalizedMessage());
        }
        return false;
    }

    public Specification<Avaliacao> especificationAvaliacaoGeneric(final Object object, final String tipo, final String fieldCampo) {

        return new Specification<Avaliacao>() {
            @Override
            public Predicate toPredicate(Root<Avaliacao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                if (object != null) {
                    if (tipo.equals("I")) {
                        if (!object.equals(0)) {
                            return cb.equal(root.get(fieldCampo), object);
                        }
                    } else if (!object.toString().trim().equals("")) {
                        return cb.equal(root.get(fieldCampo), object);
                    }

                }
                System.out.println(" valor do objeto " + object);

                return null;
            }
        };
    }

    public Avaliacao duplicar(Avaliacao avaliacao) throws Exception {
        return (Avaliacao) BeanUtils.cloneBean(avaliacao);
    }
}
