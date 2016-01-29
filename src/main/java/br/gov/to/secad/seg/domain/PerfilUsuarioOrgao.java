/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author alex.santos
 */
@Entity
@Table(name = "perfil_usuario_orgao", schema = "sad")
public class PerfilUsuarioOrgao implements Serializable {

    @Id
    @SequenceGenerator(name = "perfil_usuario_orgao_id", sequenceName = "perfil_usuario_orgao_seq", schema = "sad", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_usuario_orgao_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "perfil_usuario_id")
    private Permissao permissaoUsuario;

    @ManyToOne
    @JoinColumn(name = "orgao_id")
    private Orgao orgao;

    @Column
    private String ativo = "S";

    @Column
    private String excluido = "N";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Permissao getPermissaoUsuario() {
        return permissaoUsuario;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getExcluido() {
        return excluido;
    }

    public void setExcluido(String excluido) {
        this.excluido = excluido;
    }

    public void setPermissaoUsuario(Permissao permissaoUsuario) {
        this.permissaoUsuario = permissaoUsuario;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.permissaoUsuario);
        hash = 67 * hash + Objects.hashCode(this.orgao);
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
        final PerfilUsuarioOrgao other = (PerfilUsuarioOrgao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.permissaoUsuario, other.permissaoUsuario)) {
            return false;
        }
        if (!Objects.equals(this.orgao, other.orgao)) {
            return false;
        }
        return true;
    }

}
