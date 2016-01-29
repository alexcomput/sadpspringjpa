/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.domain;

import br.gov.to.secad.seg.util.MensagensController;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author wellyngton.santos
 */
@Entity
@Table(schema = "sad", name = "perfil_usuario")
public class Permissao implements Serializable {

    @Id
    @SequenceGenerator(name = "idperfil_usuario", sequenceName = "permissao_usuario_id_seq", schema = "sad", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idperfil_usuario")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @ManyToOne
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private Usuario usuario;

    @Column(name = "lotado_orgao_id")
    private Integer lotadoOrgao_id;

    @Column(name = "lotado_orgao")
    private String lotadoOrgao;

    @Column(name = "nome")
    private String nomeServidor;

    @Column(name = "lotado_regional_id")
    private String lotadoRegional_id;

    @Column(name = "lotado_regional")
    private String lotadoRegional;

    @Column(name = "lotado_setor_id")
    private String lotadoSetor_id;

    @Column(name = "lotado_setor")
    private String lotadoSetor;

    @OneToMany(mappedBy = "permissaoUsuario")
    private List<PerfilUsuarioOrgao> perfilUsuarioOrgaos;

    public Integer getId() {
        return id;
    }

    public String getNomeServidor() {
        return nomeServidor;
    }

    public void setNomeServidor(String nomeServidor) {
        this.nomeServidor = nomeServidor;
    }

    public List<PerfilUsuarioOrgao> getPerfilUsuarioOrgaos() {
        return perfilUsuarioOrgaos;
    }

    public void setPerfilUsuarioOrgaos(List<PerfilUsuarioOrgao> perfilUsuarioOrgaos) {
        this.perfilUsuarioOrgaos = perfilUsuarioOrgaos;
    }

    public Integer getLotadoOrgao_id() {
        return lotadoOrgao_id;
    }

    public void setLotadoOrgao_id(Integer lotadoOrgao_id) {
        this.lotadoOrgao_id = lotadoOrgao_id;
    }

    public String getLotadoOrgao() {
        return lotadoOrgao;
    }

    public void setLotadoOrgao(String lotadoOrgao) {
        this.lotadoOrgao = lotadoOrgao;
    }

    public String getLotadoRegional() {
        return lotadoRegional;
    }

    public void setLotadoRegional(String lotadoRegional) {
        this.lotadoRegional = lotadoRegional;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario cpf) {
        this.usuario = cpf;
    }

    public String getLotadoRegional_id() {
        return lotadoRegional_id;
    }

    public void setLotadoRegional_id(String lotadoRegional_id) {
        this.lotadoRegional_id = lotadoRegional_id;
    }

    public String getRegional() {
        return lotadoRegional;
    }

    public void setRegional(String regional) {
        this.lotadoRegional = regional;
    }

    public String getLotadoSetor_id() {
        return lotadoSetor_id;
    }

    public void setLotadoSetor_id(String lotadoSetor_id) {
        this.lotadoSetor_id = lotadoSetor_id;
    }

    public String getLotadoSetor() {
        return lotadoSetor;
    }

    public void setLotadoSetor(String lotadoSetor) {
        this.lotadoSetor = lotadoSetor;
    }

    public void setDadosServidor(Servidor servidor) {
        if (servidor != null) {
            //this.setOrgao(servidor.getSigla_orgao());
//            this.getOrgao().setId(servidor.getCod_orgao());
//            this.getOrgao().setNome(servidor.getSigla_orgao()); 

            this.setLotadoSetor(servidor.getSetor());
            this.setLotadoSetor_id(servidor.getSetorId());
            this.setNomeServidor(servidor.getNome());
        } else {
            System.out.println("Servidor não encontrado na Base de dados!");
            MensagensController.addFatal("Servidor não encontrado na Base de dados.");
        }
    }

}
