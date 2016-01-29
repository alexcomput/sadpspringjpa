/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.view;

import br.gov.to.secad.seg.domain.Avaliacao;
import br.gov.to.secad.seg.domain.OrgaoSetor;
import br.gov.to.secad.seg.service.AvaliacaoService;
import br.gov.to.secad.seg.service.OrgaoSetorService;
import br.gov.to.secad.seg.util.MensagensController;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alex.santos
 */
@ManagedBean
@ViewScoped
public class AvaliacaoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Avaliacao> listasAvaliacoes;

    private String matricula;

    @ManagedProperty(value = "#{avaliacaoService}")
    private AvaliacaoService avaliacaoService;

    @ManagedProperty(value = "#{orgaoSetorService}")
    private OrgaoSetorService orgaoSetorService;

    private Integer idAvaliacao = 0;

    private Integer condicao;

    private Avaliacao avaliacao;

    private List<OrgaoSetor> orgaoSetorList;

    private OrgaoSetor orgaoSetor;

    FacesContext context;

    public void init() {
        context = FacesContext.getCurrentInstance();

        orgaoSetorList = orgaoSetorService.findAll();

        if (idAvaliacao > 0) {
            try {
                if (condicao == 1) {
                    context.addMessage(null, new FacesMessage("Adicionar uma nova Etapa."));

                    this.avaliacao = avaliacaoService.duplicar(avaliacaoService.fndByEtapa(idAvaliacao));
                    this.avaliacao.setId(null);
                } else {
                    avaliacao = avaliacaoService.fndByEtapa(idAvaliacao);
                    context.addMessage(null, new FacesMessage("Serviço de alteação da Etapa."));

                }
                for (OrgaoSetor orgaoSetorLoop : orgaoSetorList) {
                    if (avaliacao.getCdorgao() == orgaoSetorLoop.getCdorgao()) {
                        orgaoSetor = orgaoSetorLoop;
                    }
                }
            } catch (Exception ex) {
                MensagensController.addError("Erro ao duplicar avaliacação entre em contato com administrador! " + ex.getMessage());
            }
        } else {
            System.out.println("Entrou aqui. valor" + idAvaliacao);

        }
    }

    public String adicionarAvali(Integer idAvaliacao) {

        return "avaliacaoAdd.xhtml?faces-redirect=true&idAvaliacao=" + idAvaliacao + "&condicao=1";
    }

    public String atualizar(Integer idAvaliacao) {

        return "avaliacaoAdd.xhtml?faces-redirect=true&idAvaliacao=" + idAvaliacao + "&condicao=2";
    }

    public void adicionarOrgao() {
        avaliacao.setCdorgao(orgaoSetor.getCdorgao());
        avaliacao.setDcorgao(orgaoSetor.getDcorgao());
    }

    public void salvar() {
        if (avaliacaoService.salvar(avaliacao)) {
            MensagensController.addInfo("Registro executado com sucesso!");
        }else{
            MensagensController.addError("Erro ao executado serviço! Por favor entre em contato com suporte");
            
        }
    }

    public OrgaoSetorService getOrgaoSetorService() {
        return orgaoSetorService;
    }

    public void setOrgaoSetorService(OrgaoSetorService orgaoSetorService) {
        this.orgaoSetorService = orgaoSetorService;
    }

    public OrgaoSetor getOrgaoSetor() {
        return orgaoSetor;
    }

    public void setOrgaoSetor(OrgaoSetor orgaoSetor) {
        this.orgaoSetor = orgaoSetor;
    }

    public List<OrgaoSetor> getOrgaoSetorList() {
        return orgaoSetorList;
    }

    public void setOrgaoSetorList(List<OrgaoSetor> orgaoSetorList) {
        this.orgaoSetorList = orgaoSetorList;
    }

    public Integer getCondicao() {
        return condicao;
    }

    public void setCondicao(Integer condicao) {
        this.condicao = condicao;
    }

    public AvaliacaoService getAvaliacaoService() {
        return avaliacaoService;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setAvaliacaoService(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public void buscarMatricula() {
        listasAvaliacoes = avaliacaoService.findByAvaliacaoMatricula(matricula);
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Avaliacao> getListasAvaliacoes() {
        return listasAvaliacoes;
    }

    public void setListasAvaliacoes(List<Avaliacao> listasAvaliacoes) {
        this.listasAvaliacoes = listasAvaliacoes;
    }

}
