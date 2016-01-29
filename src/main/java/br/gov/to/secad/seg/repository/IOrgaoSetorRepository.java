/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.repository;

import br.gov.to.secad.seg.domain.OrgaoSetor;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alex.santos
 */
public interface IOrgaoSetorRepository extends JpaRepository<OrgaoSetor, Serializable>, JpaSpecificationExecutor {

    @Query(value = "SELECT distinct cdorgao,CASE when cdorgao = 11 then 'SECRETARIA DA EDUCAÇÃO E CULTURA'   when cdorgao  <> 11 then dcorgao end as dcorgao FROM sad.sad_identidadeav WHERE cdorgao >0 ORDER BY cdorgao", nativeQuery = true)
    public List<OrgaoSetor> findOrgaosetor();

    @Query(value = "SELECT distinct cdorgao,CASE when cdorgao = 11 then 'SECRETARIA DA EDUCAÇÃO E CULTURA'   when cdorgao  <> 11 then dcorgao end as dcorgao FROM sad.sad_identidadeav WHERE cdorgao >0 and cdorgao = ?1 ORDER BY cdorgao", nativeQuery = true)
    public OrgaoSetor findOrgaosetorId(Integer orgaoid);

}
