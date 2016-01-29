/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.service;

import br.gov.to.secad.seg.domain.Permissao;
import br.gov.to.secad.seg.domain.Usuario;
import br.gov.to.secad.seg.repository.IPermissaoRepository;
import br.gov.to.secad.seg.view.LoginController;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import static org.springframework.data.jpa.domain.Specifications.where;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe que fornece os serviços para o spring manipular a classe Fator
 *
 * @author wellyngton.santos
 */
@Service
public class PermissaoService {

    /**
     * Atributo iFatorRepository usado para tratar ações na entidade Fator no
     * banco.
     */
    @Autowired
    IPermissaoRepository iPermissaoRepository;

    /**
     * Método usado para acionar o método de busca no repositório de Fator. O
     * argumento recebe o valor de um nome para ser pesquisado, o valor é setado
     * na chamada de método feita pela instância iFatorRepository.
     */
    @Transactional(timeout = 10)
    public List<Permissao> findByUsuario(Usuario usuario) {
        try {
            //System.out.println("passou service");
            return iPermissaoRepository.findByUsuario(usuario);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Transactional(timeout = 10)
    public Permissao findByCpfPerfil(String cpf, Integer idPerfil) {
        try {
            //System.out.println("passou service");
            return iPermissaoRepository.findByCpfPerfil(cpf,idPerfil);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Transactional(timeout = 10)
    public Permissao findByCpfUuario(Usuario cpf) {
        try {
            return iPermissaoRepository.findByCpfUuario(cpf);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getLocalizedMessage());
            return null;
        }
    }

    public Permissao findOnePermissao(Integer id) {
        return iPermissaoRepository.findOne(id);
    }

    public Page<Permissao> findAllPermissaoUsuario(PageRequest request, Object cpf) {
        if (request.getSort() != null) {
            request.getSort().and(new Sort(new Sort.Order(Sort.Direction.DESC, "id")));
        } else {
            PageRequest tmp = new PageRequest(request.getPageNumber(), request.getPageSize(), new Sort(new Sort.Order(Sort.Direction.DESC, "id")));
            request = tmp;
        }
        // busca a sessao 
        //LoginController logincontroller = (LoginController) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginController");
//        System.out.println(" valor id orgao " + regional_id);
        //os dois jeitos funciona        
        return iPermissaoRepository.findAll(where(especificationPermissaoFiltreG(cpf, "S", "cpf", null)).
                and(especificationPermissaoFiltreG(LoginController.getOrgaoId(), "I", "orgao_id", null)).
                and(especificationPermissaoFiltreG(LoginController.getRegionalId(), "S", "regional_id", null)).
                and(especificationPermissaoFiltreG(LoginController.getSetorId(), "S", "setor_id", null)), request);
    }

    public Specification<Permissao> especificationPermissaoFiltreG(final Object object, final String tipo,
            final String fieldCampo, final String fieldFilho1) {

        return new Specification<Permissao>() {
            @Override
            public Predicate toPredicate(Root<Permissao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (object != null) {
                    switch (tipo) {
                        case "I":
                            return cb.like(cb.lower(root.<String>get(fieldCampo)), Integer.getInteger(object.toString()) > 0 && Integer.getInteger(object.toString()) != null ? "%" + object + "%" : null);
                        case "S":
                            //Consulta quando for pelo nome do convenio
                            return cb.like(cb.lower(root.<String>get(fieldCampo)), object.toString() != null ? getLikePattern(object.toString()) : null);
                        case "O":
                            return cb.like(cb.lower(root.get(fieldCampo).<String>get(fieldFilho1)), getLikePattern(object.toString()));
                        default:
                            return null;
                    }
                }
                return null;
            }

            private String getLikePattern(final String searchTerm) {
                StringBuilder pattern = new StringBuilder();
                pattern.append("%");
                pattern.append(searchTerm.toLowerCase());
                pattern.append("%");
                return pattern.toString();
            }
        };
    }

    @Transactional
    public boolean salvar(Permissao permissaoUsuario) {
        try {
            iPermissaoRepository.save(permissaoUsuario);
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getLocalizedMessage());
            return false;
        }
        return true;
    }

}
