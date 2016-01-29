/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.ergon.repository;

import br.gov.to.secad.seg.domain.QuadroProfissional;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author wellyngton.santos Repositório para a classe QuadroProfissional,
 * responsável pelas transações com o BD que tiverem essa classe como objeto.
 */
public interface IQuadroProfissionalRepository extends JpaRepository<QuadroProfissional, Serializable> {
 
}
