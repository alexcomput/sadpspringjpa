/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.to.secad.seg.repository;

import br.gov.to.secad.seg.domain.PerfilUsuarioOrgao;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author alex.santos
 */
public interface IPerfilUsuarioOrgaoRepository  extends JpaRepository<PerfilUsuarioOrgao, Serializable>, JpaSpecificationExecutor {
    
}
