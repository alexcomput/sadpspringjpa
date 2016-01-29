/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.service;

import br.gov.to.secad.seg.domain.PerfilUsuarioOrgao;
import br.gov.to.secad.seg.repository.IPerfilUsuarioOrgaoRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import static org.springframework.data.jpa.domain.Specifications.where;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alex.santos
 */
@Service
public class PerfilUsuarioOrgaoService {

    @Autowired
    public IPerfilUsuarioOrgaoRepository iAvaliacaoRepository;

    public List<PerfilUsuarioOrgao> findByPerfilUsuarioId(Integer permissaoIdUsuario) {
        try {
            return iAvaliacaoRepository.findAll(where(especificatioPerfilUsuarioOrgao(permissaoIdUsuario, "I", "permissaoUsuario", "id", "")).
                    and(especificatioPerfilUsuarioOrgao("N", "S", "excluido", "", "")));
        } catch (Exception e) {
            System.out.println("erro ao buscar PerfilUsuarioRepositorioOrgao : " + e.getLocalizedMessage());
            return null;
        }
    }

    public List<PerfilUsuarioOrgao> findByPerfilIdUsuarioCpf(Integer permissaoIdUsuario, String usuarioCpf) {
        try {
            return iAvaliacaoRepository.findAll(where(especificatioPerfilUsuarioOrgao(permissaoIdUsuario, "I", "permissaoUsuario", "id", "")).
                    and(especificatioPerfilUsuarioOrgao(usuarioCpf, "S", "permissaoUsuario", "usuario", "cpf")).
                    and(especificatioPerfilUsuarioOrgao("N", "S", "excluido", "", "")));
        } catch (Exception e) {
            System.out.println("erro ao buscar PerfilUsuarioRepositorioOrgao : " + e.getLocalizedMessage());
            return null;
        }
    }

    public PerfilUsuarioOrgao findByPerfilUsuarioOrgaoId(Integer perfilUsuarioOrgaoId) {
        try {
            return (PerfilUsuarioOrgao) iAvaliacaoRepository.findOne(where(especificatioPerfilUsuarioOrgao(perfilUsuarioOrgaoId, "I", "id", "", null)).
                    and(especificatioPerfilUsuarioOrgao("N", "S", "excluido", "", "")));
        } catch (Exception e) {
            System.out.println("erro ao buscar PerfilUsuarioRepositorioOrgao : " + e.getLocalizedMessage());
            return null;
        }
    }

    public Specification<PerfilUsuarioOrgao> especificatioPerfilUsuarioOrgao(final Object object, final String tipo, final String fieldCampo, final String filhoField, final String filhoFilhoField) {

        return new Specification<PerfilUsuarioOrgao>() {
            @Override
            public Predicate toPredicate(Root<PerfilUsuarioOrgao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (object != null) {
                    query.distinct(true);
                    if (tipo.equals("I")) {
                        if (!object.equals(0)) {
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

    @Transactional
    public boolean salvar(PerfilUsuarioOrgao perfilUsuarioOrgao) {
        try {
            iAvaliacaoRepository.save(perfilUsuarioOrgao);
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getLocalizedMessage());
            return false;
        }
        return true;
    }

    @Transactional
    public boolean remover(PerfilUsuarioOrgao perfilUsuarioOrgao) {
        try {
            perfilUsuarioOrgao.setExcluido("S");
            iAvaliacaoRepository.save(perfilUsuarioOrgao);
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getLocalizedMessage());
            return false;
        }
        return true;
    }

}
