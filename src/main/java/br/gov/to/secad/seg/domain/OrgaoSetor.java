/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 Tabela responsavel para trazer todos os orgão cadastrados na tabela de avaliações

 */
package br.gov.to.secad.seg.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author alex.santos
 */
@Entity
public class OrgaoSetor implements Serializable {

    @Id
    private Integer cdorgao;

    @Column
    private String dcorgao;

    public Integer getCdorgao() {
        return cdorgao;
    }

    public void setCdorgao(Integer cdorgao) {
        this.cdorgao = cdorgao;
    }

    public String getDcorgao() {
        return dcorgao;
    }

    public void setDcorgao(String dcorgao) {
        this.dcorgao = dcorgao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.cdorgao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrgaoSetor other = (OrgaoSetor) obj;
        if (!Objects.equals(this.cdorgao, other.cdorgao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrgaoSetor{" + "cdorgao=" + cdorgao + ", dcorgao=" + dcorgao + '}';
    }
 

}
