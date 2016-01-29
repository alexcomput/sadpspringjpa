/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.domain;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alex.santos
 */
@Entity
@Table(name = "sad_identidadeav", schema = "sad")
public class Avaliacao {

    @Id
    @SequenceGenerator(name = "cdidentidadeav", sequenceName = "sad_sad_identidadeav_seq", schema = "sad", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cdidentidadeav")
    @Column(name = "cdidentidadeav")
    private Integer id;

    private String matricula;

    private String cdcargo;

    private Integer numeroav;

    private Integer cdconceito;

    private Integer cdunidade;

    private Integer cdsituacaoav;

    private String nome;

    private Integer cdorgao;

    private Integer cdcenario;

    private Integer idautorcenario;

    @Column(name = "dtautorcenario")
    @Temporal(TemporalType.DATE)
    private Date dtautorcenario;

    private String cdquestionario;
    private Integer idautorquestionario;

    @Column(name = "dtautorquestionario")
    @Temporal(TemporalType.DATE)
    private Date dtautorquestionario;

    @Column(name = "periodoinicial")
    @Temporal(TemporalType.DATE)
    private Date periodoinicial;

    @Column(name = "periodofinal")
    @Temporal(TemporalType.DATE)
    private Date periodofinal;
    private Integer idautorizante;
    private String historico;
    private Integer idautorexclogica;

    @Column(name = "dtautorexclogica")
    @Temporal(TemporalType.DATE)
    private Date dtautorexclogica;

    private Integer cdunidlocalizacaoatual;
    private double notafinal;
    private boolean flaprovacao;
    private Integer via;
    private boolean flfluxo;

    @Column(name = "dtcriacao")
    @Temporal(TemporalType.DATE)
    private Date dtcriacao;

    @Column(name = "dtatualizacao")
    @Temporal(TemporalType.DATE)
    private Date dtatualizacao;

    private Integer idresolveproblema;

    private boolean flavaliar;

    private Integer cdmunicipio;

    @Column(name = "dtadmissao")
    @Temporal(TemporalType.DATE)
    private Date dtadmissao;

    @Column(name = "dtdevolvsecad")
    @Temporal(TemporalType.DATE)
    private Date dtdevolvsecad;

    @Column(name = "dtdevolvcomissao")
    @Temporal(TemporalType.DATE)
    private Date dtdevolvcomissao;

    private String obs;
    private boolean flexclogica;
    private String dccargoefetivo;
    private String dcmunicipio;
    private String dcorgao;

    @Column(name = "dttabulacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dttabulacao;

    private String tprestricaonota;

    @Column(name = "dtautorcancel")
    @Temporal(TemporalType.DATE)
    private Date dtautorcancel;

    @Column(name = "auditoriaperiodoinicial")
    @Temporal(TemporalType.DATE)
    private Date auditoriaperiodoinicial;

    @Column(name = "auditoriaperiodofinal")
    @Temporal(TemporalType.DATE)
    private Date auditoriaperiodofinal;

    private String agrupamentoimpressao;
    private String dcunidade;
    private String disposicao;
    private String matriculamembrotabulacao;
    private boolean flforcaravaliacao;

    @Column(name = "dtenvioarquivo")
    @Temporal(TemporalType.DATE)
    private Date dtenvioarquivo;

    private boolean flignoraridi;
    private String dcignoraridi;
    @Column(name = "assinatura_interna")
    private String assinaturaInterna;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCdcargo() {
        return cdcargo;
    }

    public void setCdcargo(String cdcargo) {
        this.cdcargo = cdcargo;
    }

    public Integer getNumeroav() {
        return numeroav;
    }

    public void setNumeroav(Integer numeroav) {
        this.numeroav = numeroav;
    }

    public Integer getCdconceito() {
        return cdconceito;
    }

    public void setCdconceito(Integer cdconceito) {
        this.cdconceito = cdconceito;
    }

    public Integer getCdunidade() {
        return cdunidade;
    }

    public void setCdunidade(Integer cdunidade) {
        this.cdunidade = cdunidade;
    }

    public Integer getCdsituacaoav() {
        return cdsituacaoav;
    }

    public void setCdsituacaoav(Integer cdsituacaoav) {
        this.cdsituacaoav = cdsituacaoav;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCdorgao() {
        return cdorgao;
    }

    public void setCdorgao(Integer cdorgao) {
        this.cdorgao = cdorgao;
    }

    public Integer getCdcenario() {
        return cdcenario;
    }

    public void setCdcenario(Integer cdcenario) {
        this.cdcenario = cdcenario;
    }

    public Integer getIdautorcenario() {
        return idautorcenario;
    }

    public void setIdautorcenario(Integer idautorcenario) {
        this.idautorcenario = idautorcenario;
    }

    public Date getDtautorcenario() {
        return dtautorcenario;
    }

    public void setDtautorcenario(Date dtautorcenario) {
        this.dtautorcenario = dtautorcenario;
    }

    public String getCdquestionario() {
        return cdquestionario;
    }

    public void setCdquestionario(String cdquestionario) {
        this.cdquestionario = cdquestionario;
    }

    public Integer getIdautorquestionario() {
        return idautorquestionario;
    }

    public void setIdautorquestionario(Integer idautorquestionario) {
        this.idautorquestionario = idautorquestionario;
    }

    public Date getDtautorquestionario() {
        return dtautorquestionario;
    }

    public void setDtautorquestionario(Date dtautorquestionario) {
        this.dtautorquestionario = dtautorquestionario;
    }

    public Date getPeriodoinicial() {
        return periodoinicial;
    }

    public void setPeriodoinicial(Date periodoinicial) {
        this.periodoinicial = periodoinicial;
    }

    public Date getPeriodofinal() {
        return periodofinal;
    }

    public void setPeriodofinal(Date periodofinal) {
        this.periodofinal = periodofinal;
    }

    public Integer getIdautorizante() {
        return idautorizante;
    }

    public void setIdautorizante(Integer idautorizante) {
        this.idautorizante = idautorizante;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Integer getIdautorexclogica() {
        return idautorexclogica;
    }

    public void setIdautorexclogica(Integer idautorexclogica) {
        this.idautorexclogica = idautorexclogica;
    }

    public Date getDtautorexclogica() {
        return dtautorexclogica;
    }

    public void setDtautorexclogica(Date dtautorexclogica) {
        this.dtautorexclogica = dtautorexclogica;
    }

    public Integer getCdunidlocalizacaoatual() {
        return cdunidlocalizacaoatual;
    }

    public void setCdunidlocalizacaoatual(Integer cdunidlocalizacaoatual) {
        this.cdunidlocalizacaoatual = cdunidlocalizacaoatual;
    }

    public double getNotafinal() {
        return notafinal;
    }

    public void setNotafinal(double notafinal) {
        this.notafinal = notafinal;
    }

    public boolean isFlaprovacao() {
        return flaprovacao;
    }

    public void setFlaprovacao(boolean flaprovacao) {
        this.flaprovacao = flaprovacao;
    }

    public Integer getVia() {
        return via;
    }

    public void setVia(Integer via) {
        this.via = via;
    }

    public boolean isFlfluxo() {
        return flfluxo;
    }

    public void setFlfluxo(boolean flfluxo) {
        this.flfluxo = flfluxo;
    }

    public Date getDtcriacao() {
        return dtcriacao;
    }

    public void setDtcriacao(Date dtcriacao) {
        this.dtcriacao = dtcriacao;
    }

    public Date getDtatualizacao() {
        return dtatualizacao;
    }

    public void setDtatualizacao(Date dtatualizacao) {
        this.dtatualizacao = dtatualizacao;
    }

    public Integer getIdresolveproblema() {
        return idresolveproblema;
    }

    public void setIdresolveproblema(Integer idresolveproblema) {
        this.idresolveproblema = idresolveproblema;
    }

    public boolean isFlavaliar() {
        return flavaliar;
    }

    public void setFlavaliar(boolean flavaliar) {
        this.flavaliar = flavaliar;
    }

    public Integer getCdmunicipio() {
        return cdmunicipio;
    }

    public void setCdmunicipio(Integer cdmunicipio) {
        this.cdmunicipio = cdmunicipio;
    }

    public Date getDtadmissao() {
        return dtadmissao;
    }

    public void setDtadmissao(Date dtadmissao) {
        this.dtadmissao = dtadmissao;
    }

    public Date getDtdevolvsecad() {
        return dtdevolvsecad;
    }

    public void setDtdevolvsecad(Date dtdevolvsecad) {
        this.dtdevolvsecad = dtdevolvsecad;
    }

    public Date getDtdevolvcomissao() {
        return dtdevolvcomissao;
    }

    public void setDtdevolvcomissao(Date dtdevolvcomissao) {
        this.dtdevolvcomissao = dtdevolvcomissao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public boolean isFlexclogica() {
        return flexclogica;
    }

    public void setFlexclogica(boolean flexclogica) {
        this.flexclogica = flexclogica;
    }

    public String getDccargoefetivo() {
        return dccargoefetivo;
    }

    public void setDccargoefetivo(String dccargoefetivo) {
        this.dccargoefetivo = dccargoefetivo;
    }

    public String getDcmunicipio() {
        return dcmunicipio;
    }

    public void setDcmunicipio(String dcmunicipio) {
        this.dcmunicipio = dcmunicipio;
    }

    public String getDcorgao() {
        return dcorgao;
    }

    public void setDcorgao(String dcorgao) {
        this.dcorgao = dcorgao;
    }

    public Date getDttabulacao() {
        return dttabulacao;
    }

    public void setDttabulacao(Date dttabulacao) {
        this.dttabulacao = dttabulacao;
    }

    public String getTprestricaonota() {
        return tprestricaonota;
    }

    public void setTprestricaonota(String tprestricaonota) {
        this.tprestricaonota = tprestricaonota;
    }

    public Date getDtautorcancel() {
        return dtautorcancel;
    }

    public void setDtautorcancel(Date dtautorcancel) {
        this.dtautorcancel = dtautorcancel;
    }

    public Date getAuditoriaperiodoinicial() {
        return auditoriaperiodoinicial;
    }

    public void setAuditoriaperiodoinicial(Date auditoriaperiodoinicial) {
        this.auditoriaperiodoinicial = auditoriaperiodoinicial;
    }

    public Date getAuditoriaperiodofinal() {
        return auditoriaperiodofinal;
    }

    public void setAuditoriaperiodofinal(Date auditoriaperiodofinal) {
        this.auditoriaperiodofinal = auditoriaperiodofinal;
    }

    public String getAgrupamentoimpressao() {
        return agrupamentoimpressao;
    }

    public void setAgrupamentoimpressao(String agrupamentoimpressao) {
        this.agrupamentoimpressao = agrupamentoimpressao;
    }

    public String getDcunidade() {
        return dcunidade;
    }

    public void setDcunidade(String dcunidade) {
        this.dcunidade = dcunidade;
    }

    public String getDisposicao() {
        return disposicao;
    }

    public void setDisposicao(String disposicao) {
        this.disposicao = disposicao;
    }

    public String getMatriculamembrotabulacao() {
        return matriculamembrotabulacao;
    }

    public void setMatriculamembrotabulacao(String matriculamembrotabulacao) {
        this.matriculamembrotabulacao = matriculamembrotabulacao;
    }

    public boolean isFlforcaravaliacao() {
        return flforcaravaliacao;
    }

    public void setFlforcaravaliacao(boolean flforcaravaliacao) {
        this.flforcaravaliacao = flforcaravaliacao;
    }

    public Date getDtenvioarquivo() {
        return dtenvioarquivo;
    }

    public void setDtenvioarquivo(Date dtenvioarquivo) {
        this.dtenvioarquivo = dtenvioarquivo;
    }

    public boolean isFlignoraridi() {
        return flignoraridi;
    }

    public void setFlignoraridi(boolean flignoraridi) {
        this.flignoraridi = flignoraridi;
    }

    public String getDcignoraridi() {
        return dcignoraridi;
    }

    public void setDcignoraridi(String dcignoraridi) {
        this.dcignoraridi = dcignoraridi;
    }

    public String getAssinaturaInterna() {
        return assinaturaInterna;
    }

    public void setAssinaturaInterna(String assinaturaInterna) {
        this.assinaturaInterna = assinaturaInterna;
    }

}
