/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.ergon.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alex.santos
 */
@Entity
@Table(name = "GTO_AVALIACAO_PROB", schema = "C_DTI")
public class AvaliacaoErgon implements Serializable {

    @Id
    @Column(name = "NUMFUNC")
    private Integer numFunc;
    @Id
    @Column(name = "NUMVINC")
    private Integer numVinc;

    @Column(name = "DTGERACAO")
    @Temporal(TemporalType.DATE)
    private Date dtGeracao;

    @Column(name = "CPF")
    private Number cpf;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "QUADRO")
    private String quadro;

    @Column(name = "DTADMISSAO")
    @Temporal(TemporalType.DATE)
    private Date dtAdmissao;

    @Column(name = "DTEXERCICIO")
    @Temporal(TemporalType.DATE)
    private Date dtExercicio;
    @Id
    @Column(name = "ETAPA")
    private Integer etapa;

    @Column(name = "DTINI")
    @Temporal(TemporalType.DATE)
    private Date dtIni;

    @Column(name = "DTFIM")
    @Temporal(TemporalType.DATE)
    private Date dtFim;

    @Column(name = "DTAVALIACAO")
    @Temporal(TemporalType.DATE)
    private Date dtAvaliacao;

    @Column(name = "CARGO_ID")
    private Integer cargoId;

    @Column(name = "CARGO_NOME")
    private String cargoNome;

    @Column(name = "FUNCAO_ID")
    private String funcao_id;

    @Column(name = "FUNCAO_NOME")
    private String funcaoNome;

    @Column(name = "ORGAO_ID")
    private Integer orgaoId;

    @Column(name = "ORGAO_NOME")
    private String orgaoNome;

    @Column(name = "ORGAO_SIGLA")
    private String orgaoSigla;

    @Column(name = "REGIONAL_ID")
    private String regionalId;

    @Column(name = "REGIONAL_NOME")
    private String regionalNome;

    @Column(name = "REGIONAL_MUNICIPIO")
    private String regionalMunicipio;

    @Column(name = "REGIONAL_UF")
    private String regionalUF;

    @Column(name = "SETOR_ID")
    private String setorId;

    @Column(name = "SETOR_NOME")
    private String setorNome;
    @Column(name = "SETOR_MUNICIPIO")
    private String setorMunicipio;

    @Column(name = "SETOR_UF")
    private String setorUF;

    public Integer getNumFunc() {
        return numFunc;
    }

    public void setNumFunc(Integer numFunc) {
        this.numFunc = numFunc;
    }

    public Integer getNumVinc() {
        return numVinc;
    }

    public void setNumVinc(Integer numVinc) {
        this.numVinc = numVinc;
    }

    public Date getDtGeracao() {
        return dtGeracao;
    }

    public void setDtGeracao(Date dtGeracao) {
        this.dtGeracao = dtGeracao;
    }

    public Number getCpf() {
        return cpf;
    }

    public void setCpf(Number cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuadro() {
        return quadro;
    }

    public void setQuadro(String quadro) {
        this.quadro = quadro;
    }

    public Date getDtAdmissao() {
        return dtAdmissao;
    }

    public void setDtAdmissao(Date dtAdmissao) {
        this.dtAdmissao = dtAdmissao;
    }

    public Date getDtExercicio() {
        return dtExercicio;
    }

    public void setDtExercicio(Date dtExercicio) {
        this.dtExercicio = dtExercicio;
    }

    public Integer getEtapa() {
        return etapa;
    }

    public void setEtapa(Integer etapa) {
        this.etapa = etapa;
    }

    public Date getDtIni() {
        return dtIni;
    }

    public void setDtIni(Date dtIni) {
        this.dtIni = dtIni;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public Date getDtAvaliacao() {
        return dtAvaliacao;
    }

    public void setDtAvaliacao(Date dtAvaliacao) {
        this.dtAvaliacao = dtAvaliacao;
    }

    public Integer getCargoId() {
        return cargoId;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }

    public String getCargoNome() {
        return cargoNome;
    }

    public void setCargoNome(String cargoNome) {
        this.cargoNome = cargoNome;
    }

    public String getFuncao_id() {
        return funcao_id;
    }

    public void setFuncao_id(String funcao_id) {
        this.funcao_id = funcao_id;
    }

    public String getFuncaoNome() {
        return funcaoNome;
    }

    public void setFuncaoNome(String funcaoNome) {
        this.funcaoNome = funcaoNome;
    }

    public Integer getOrgaoId() {
        return orgaoId;
    }

    public void setOrgaoId(Integer orgaoId) {
        this.orgaoId = orgaoId;
    }

    public String getOrgaoNome() {
        return orgaoNome;
    }

    public void setOrgaoNome(String orgaoNome) {
        this.orgaoNome = orgaoNome;
    }

    public String getOrgaoSigla() {
        return orgaoSigla;
    }

    public void setOrgaoSigla(String orgaoSigla) {
        this.orgaoSigla = orgaoSigla;
    }

    public String getRegionalId() {
        return regionalId;
    }

    public void setRegionalId(String regionalId) {
        this.regionalId = regionalId;
    }

    public String getRegionalNome() {
        return regionalNome;
    }

    public void setRegionalNome(String regionalNome) {
        this.regionalNome = regionalNome;
    }

    public String getRegionalMunicipio() {
        return regionalMunicipio;
    }

    public void setRegionalMunicipio(String regionalMunicipio) {
        this.regionalMunicipio = regionalMunicipio;
    }

    public String getRegionalUF() {
        return regionalUF;
    }

    public void setRegionalUF(String regionalUF) {
        this.regionalUF = regionalUF;
    }

    public String getSetorId() {
        return setorId;
    }

    public void setSetorId(String setorId) {
        this.setorId = setorId;
    }

    public String getSetorNome() {
        return setorNome;
    }

    public void setSetorNome(String setorNome) {
        this.setorNome = setorNome;
    }

    public String getSetorMunicipio() {
        return setorMunicipio;
    }

    public void setSetorMunicipio(String setorMunicipio) {
        this.setorMunicipio = setorMunicipio;
    }

    public String getSetorUF() {
        return setorUF;
    }

    public void setSetorUF(String setorUF) {
        this.setorUF = setorUF;
    }

}
