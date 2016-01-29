/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.service;

import br.gov.to.secad.seg.domain.Orgao;
import br.gov.to.secad.seg.domain.OrgaoSetor;
import br.gov.to.secad.seg.repository.IOrgaoSetorRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import static org.springframework.data.jpa.domain.Specifications.where;
import org.springframework.stereotype.Service;

/**
 *
 * @author alex.santos
 */
@Service
public class OrgaoSetorService {

    @Autowired
    private IOrgaoSetorRepository iorgaoRepository;

    public List<OrgaoSetor> findAll() {
        try {
            return iorgaoRepository.findOrgaosetor();
        } catch (Exception ex) {
            System.err.println(" Erro 78584" + ex.getMessage());
        }
        return null;
    }
    public OrgaoSetor findAllOrgaoSetorId(Integer idOrgaoSetor) {
        try {
            return iorgaoRepository.findOrgaosetorId(idOrgaoSetor);
        } catch (Exception ex) {
            System.err.println(" Erro 78584" + ex.getMessage());
        }
        return null;
    } 

    public Specification<OrgaoSetor> especificatioOrgaoSetor(final Object object, final String tipo, final String fieldCampo, final String filhoField, final String filhoFilhoField) {

        return new Specification<OrgaoSetor>() {
            @Override
            public Predicate toPredicate(Root<OrgaoSetor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (object != null) {
                    query.distinct(true);
                    if (tipo.equals("I")) {
                        if (!object.equals(0) && !object.equals(null)) {
                            if (!filhoField.equals("")) {
                                if (!filhoFilhoField.equals("")) {
                                    return cb.equal(root.get(fieldCampo).get(filhoField).get(filhoFilhoField), object);
                                } else {
                                    return cb.equal(root.get(fieldCampo).get(filhoField), object);
                                }
                            } else {
                                return cb.equal(root.get(fieldCampo), object);
                            }
                        }
                    } else if (!object.toString().trim().equals("")) {
                        if (!filhoField.equals("")) {
                            if (!filhoFilhoField.equals("")) {
                                return cb.equal(root.get(fieldCampo).get(filhoField).get(filhoFilhoField), object);
                            } else {
                                return cb.equal(root.get(fieldCampo).get(filhoField), object);
                            }
                        } else {
                            return cb.equal(root.get(fieldCampo), object);
                        }
                    }
                }
                return null;
            }
        };
    }
}
