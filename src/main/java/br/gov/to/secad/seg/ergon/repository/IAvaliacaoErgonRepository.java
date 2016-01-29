/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.ergon.repository;

import br.gov.to.secad.seg.ergon.domain.AvaliacaoErgon;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interface que estende a classe JpaRepository, responsável por persistir ações
 * referentes a classe Fator.
 *
 *
 *
 * @author alex.santos
 */
public interface IAvaliacaoErgonRepository extends JpaRepository<AvaliacaoErgon, Serializable>, JpaSpecificationExecutor {

    /**
     *
     */
    @Query(value = " SELECT DISTINCT ETAPA, NUMFUNC, NUMVINC, CARGO_ID, CARGO_NOME, CPF, DTADMISSAO, DTAVALIACAO, DTEXERCICIO, DTFIM, DTGERACAO, DTINI, FUNCAO_NOME, "
            + " FUNCAO_ID, NOME, ORGAO_ID, ORGAO_NOME, ORGAO_SIGLA, QUADRO, REGIONAL_ID, REGIONAL_MUNICIPIO, REGIONAL_NOME, REGIONAL_UF, SETOR_ID, SETOR_MUNICIPIO, "
            + " SETOR_NOME, SETOR_UF FROM C_DTI.GTO_AVALIACAO_PROB WHERE  DTINI between   ?1 and ?2 ", nativeQuery = true)
    public List<AvaliacaoErgon> findByDtInicioDtFim( String dtInicio,  String dtFim);
}
