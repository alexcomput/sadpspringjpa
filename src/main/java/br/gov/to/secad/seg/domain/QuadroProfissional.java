package br.gov.to.secad.seg.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author wellyngton.santos Classe que representa um quadro profissional. Na
 * estrutura do ERGON, um quadro profissional é uma agrupamento das categorias
 * profissionais em seus diferentes regimentos.
 */
@Entity
@Table(name = "CATEGORIAS")
public class QuadroProfissional implements Serializable {

    /**
     * Atributo id - é sigla que representa a classe.
     */
    @Id
    private String sigla;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return sigla;
    }

}
