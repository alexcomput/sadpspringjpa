/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.service;

import br.gov.to.secad.seg.domain.QuadroProfissional;
import br.gov.to.secad.seg.ergon.repository.IQuadroProfissionalRepository;
import java.sql.CallableStatement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe que fornece os serviços para o spring manipular a classe
 * QuadroProfissional
 *
 * @author wellyngton.santos
 */
@Service
public class QuadroProfissionalService {

    /**
     * Atributo iQuadroProfissionalRepository usado para tratar ações na
     * entidade QuadroProfissional no banco.
     */
    @Autowired
    IQuadroProfissionalRepository iQuadroProfissionalRepository;

    /**
     * Método usado que retorna a lista de todos os quadros profissionais
     * cadastrados até o momento.
     *
     * @return
     */
    @Transactional(timeout = 10)
    public List<QuadroProfissional> findAll() {
        try {
            //System.out.println("buscando lista");

            return iQuadroProfissionalRepository.findAll();
        } catch (Exception e) {
            System.out.println("ERRO Service: " + e.getLocalizedMessage());
            return null;
        }
    }

}
