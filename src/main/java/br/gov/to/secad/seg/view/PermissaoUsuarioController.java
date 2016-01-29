package br.gov.to.secad.seg.view;

import br.gov.to.secad.seg.domain.Orgao;
import br.gov.to.secad.seg.domain.Perfil;
import br.gov.to.secad.seg.domain.PerfilUsuarioOrgao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import br.gov.to.secad.seg.service.PermissaoService;
import br.gov.to.secad.seg.domain.Permissao;
import br.gov.to.secad.seg.domain.Servidor;
import br.gov.to.secad.seg.domain.Usuario;
import br.gov.to.secad.seg.service.OrgaoService;
import br.gov.to.secad.seg.service.PerfilUsuarioOrgaoService;
import br.gov.to.secad.seg.service.ServidorService;
import br.gov.to.secad.seg.service.UsuarioService;
import br.gov.to.secad.seg.util.JDialogoUtil;
import br.gov.to.secad.seg.util.MensagensController;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Classe que controla as ações relacionadas a classe Fator.
 *
 * @author wellyngton.santos
 */
/**
 * Definição de objeto no springframework, definido como um controller.
 * Definindo o scopo da classe que será de sessão. Declaração de ManagedBean do
 * JSF
 */
@ManagedBean
@ViewScoped
public class PermissaoUsuarioController implements Serializable {
    
    @ManagedProperty(value = "#{permissaoService}")
    private PermissaoService permissaoService;
    
    @ManagedProperty(value = "#{orgaoService}")
    private OrgaoService orgaoService;
    
    private Usuario UsuarioSelecionado;
    
    private Permissao permissaoUsuario;
    
    private Perfil perfil;
    
    private Integer nunFunc;
    private Integer nunVinc;
    
    private String cpfServidor;
    
    private Servidor servidor;
    private List<Orgao> listaOrgao;
    
    private Orgao orgao;

    /**
     * Atributo que instancia o serviço de transações com a classe Servidor
     */
    @ManagedProperty(value = "#{servidorService}")
    private ServidorService servidorService;
    
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;
    
    @ManagedProperty(value = "#{perfilUsuarioOrgaoService}")
    private PerfilUsuarioOrgaoService perfilUsuarioOrgaoService;
    
    private PerfilUsuarioOrgao perfilUsuarioOrgao;

    // Lista de Orgaos que o usuario contém privilégio.
    private List<PerfilUsuarioOrgao> listaPerfilUsuarioOrgao;
    /*
     list é uma instancia do DATAMODEL da dataTable
     */
    private transient LazyDataModel<Permissao> list;
    
    @PostConstruct
    public void init() {
        listaOrgao = orgaoService.findAll();
        permissaoUsuario = new Permissao();
        this.list = new LazyDataModel<Permissao>() {
            @Override
            public List<Permissao> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

                //Variavel de retorno
                List<Permissao> result;
                //Seta o tamanho da pagina
                this.setPageSize(pageSize);
                
                if (sortOrder == SortOrder.UNSORTED || StringUtils.isBlank(sortField)) {
                    PageRequest request = new PageRequest(first / pageSize, pageSize);
                    Page<Permissao> page;
                    page = permissaoService.findAllPermissaoUsuario(request,
                            filters.get("cpf"));
                    this.setRowCount((int) page.getTotalElements());
                    result = page.getContent();
                } else {
                    Sort sort = new Sort(sortOrder == SortOrder.ASCENDING ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
                    
                    PageRequest request = new PageRequest(first / pageSize, pageSize, sort);
                    Page<Permissao> page = permissaoService.findAllPermissaoUsuario(request, filters.get("cpf"));
                    this.setRowCount((int) page.getTotalElements());
                    result = page.getContent();
                }
                // retorna a lista com os registro
                return result;
            }
            
            @Override
            public Object getRowKey(Permissao object) {
                return object.getId().toString();
            }
            
            @Override
            public Permissao getRowData(String rowKey) {
                return permissaoService.findOnePermissao(Integer.parseInt(rowKey));
            }
        };
    }
    
    public void vereficaListaPermissao() {
        
    }
    
    public void limparCampos2() {
        servidor = null;
        permissaoUsuario = new Permissao();
        this.cpfServidor = "";
        listaPerfilUsuarioOrgao = null;
    }
    
    public void carregarServidor() {
        limparCampos2();
        //permissaoUsuario = new Permissao();
        if (this.nunFunc > 0 && this.nunVinc > 0) {
            servidor = servidorService.findByNumfuncNunvinc(this.nunFunc, this.nunVinc);
            if (servidor != null) {
                Usuario usuarioBanco = usuarioService.findByCpf(servidor.cpfConverteString(servidor.getCpf()));
                if (usuarioBanco != null) {
                    permissaoUsuario.setUsuario(usuarioBanco);
                    permissaoUsuario.setNomeServidor(servidor.getNome());
                    buscarListaPerfilUsuarioCpf();
                    cpfServidor = servidor.getCpf();
                    MensagensController.addInfo("Servidor Carregado!");
                } else {
                    MensagensController.addError("usuário não contém no portal do servidor!");
                }
            } else {
                MensagensController.addError("Número de matrícula errado ou servidor não cadastrado na base de dados do ERGON!");
            }
            //permissaoService.findByUsuario(servidor);

        } else {
            MensagensController.addError("Erro ao carregar registro!");
        }
    }

    /*
     Pega o evento do JDIALOG que foi selecionado e atribui para o concedente
     */
    public void usuarioJDialogSelecionado(SelectEvent event) {
        permissaoUsuario.setUsuario((Usuario) event.getObject());
        this.cpfServidor = permissaoUsuario.getUsuario().getCpf();
        this.servidor = servidorService.findByCpfInterno(permissaoUsuario.getUsuario().getCpf());
        // Atribui as informações de onde é o servidor se o mesmo é de uma regional do orgao ETC
        permissaoUsuario.setDadosServidor(servidor);
        buscarListaPerfilUsuarioCpf();
    }
    
    public void buscarListaPerfilUsuarioCpf() {
        listaPerfilUsuarioOrgao = perfilUsuarioOrgaoService.findByPerfilIdUsuarioCpf(permissaoUsuario.getId(), permissaoUsuario.getUsuario().getCpf());
        
    }
    
    public void addPrivilegioUsuario() {
        
        Permissao permissaoUsuarioL = this.permissaoService.findByCpfPerfil(permissaoUsuario.getUsuario().getCpf(), perfil.getId());

        // Se nao conter permissao entao salva a mesma.. 
        if (permissaoUsuarioL == null) {
            this.permissaoUsuario.setPerfil(perfil);
            permissaoUsuario.setLotadoOrgao(this.orgao.getNome());
            permissaoUsuario.setLotadoOrgao_id(this.orgao.getId());
            //permissaoUsuario.setNomeServidor(this.servidor.getNome());
            ///permissaoUsuario.setNomeServidor(this.orgao.getId());            
            permissaoUsuarioL = permissaoUsuario;
            if (!this.permissaoService.salvar(permissaoUsuario)) {
                MensagensController.addInfo("Erro ao registrar registro!");
            }
        }
        perfilUsuarioOrgao = new PerfilUsuarioOrgao();
        perfilUsuarioOrgao.setOrgao(this.orgao);
        perfilUsuarioOrgao.setPermissaoUsuario(permissaoUsuarioL);
        if (perfilUsuarioOrgaoService.salvar(perfilUsuarioOrgao)) {
            MensagensController.addInfo("Registro inserido com sucesso!");
            listaPerfilUsuarioOrgao.add(perfilUsuarioOrgao);
        } else {
            MensagensController.addInfo("Erro ao registrar registro!");
        }
    }
    
    public void limparCampos() {
        servidor = null;
        this.orgao = null;
        this.perfil = null;
        permissaoUsuario = new Permissao();
        this.cpfServidor = "";
        listaPerfilUsuarioOrgao = null;
        this.nunFunc = null;
        this.nunVinc = null;
        
    }
    
    public void removerPerfilUsuarioOrgao(PerfilUsuarioOrgao perfilUsuarioOrgao) {
        if (perfilUsuarioOrgaoService.remover(perfilUsuarioOrgao)) {
            listaPerfilUsuarioOrgao.remove(perfilUsuarioOrgao);
            MensagensController.addInfo("Registro excluído com sucesso!");
        } else {
            MensagensController.addInfo("Erro ao excluído registro!");
        }
    }
    
    public void abrirJDialogUsuario() {
        JDialogoUtil.abrirDialogo("usuarioJdialogView", 600, true, false);
    }
    /*
     Fechar o dialog e enviar por envente o objeto selecionado.
     */
    
    public void selecionardoUsuario(Usuario usuario) {
        RequestContext.getCurrentInstance().closeDialog(usuario);
    }
    
    public void onEdit(RowEditEvent event) {
        
    }
    
    public void onCancel(RowEditEvent event) {
    }
    /*
     GETS E SETS
     */
    
    public List<PerfilUsuarioOrgao> getListaPerfilUsuarioOrgao() {
        return listaPerfilUsuarioOrgao;
    }
    
    public void setListaPerfilUsuarioOrgao(List<PerfilUsuarioOrgao> listaPerfilUsuarioOrgao) {
        this.listaPerfilUsuarioOrgao = listaPerfilUsuarioOrgao;
    }
    
    public PerfilUsuarioOrgaoService getPerfilUsuarioOrgaoService() {
        return perfilUsuarioOrgaoService;
    }
    
    public void setPerfilUsuarioOrgaoService(PerfilUsuarioOrgaoService perfilUsuarioOrgaoService) {
        this.perfilUsuarioOrgaoService = perfilUsuarioOrgaoService;
    }
    
    public PerfilUsuarioOrgao getPerfilUsuarioOrgao() {
        return perfilUsuarioOrgao;
    }
    
    public void setPerfilUsuarioOrgao(PerfilUsuarioOrgao perfilUsuarioOrgao) {
        this.perfilUsuarioOrgao = perfilUsuarioOrgao;
    }
    
    public Integer getNunFunc() {
        return nunFunc;
    }
    
    public void setNunFunc(Integer nunFunc) {
        this.nunFunc = nunFunc;
    }
    
    public Integer getNunVinc() {
        return nunVinc;
    }
    
    public void setNunVinc(Integer nunVinc) {
        this.nunVinc = nunVinc;
    }
    
    public String getCpfServidor() {
        return cpfServidor;
    }
    
    public Orgao getOrgao() {
        return orgao;
    }
    
    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }
    
    public List<Orgao> getListaOrgao() {
        return listaOrgao;
    }
    
    public void setListaOrgao(List<Orgao> listaOrgao) {
        this.listaOrgao = listaOrgao;
    }
    
    public OrgaoService getOrgaoService() {
        return orgaoService;
    }
    
    public void setOrgaoService(OrgaoService orgaoService) {
        this.orgaoService = orgaoService;
    }
    
    public void setCpfServidor(String cpfServidor) {
        this.cpfServidor = cpfServidor;
    }
    
    public Permissao getPermissaoUsuario() {
        return permissaoUsuario;
    }
    
    public void setPermissaoUsuario(Permissao permissaoUsuario) {
        this.permissaoUsuario = permissaoUsuario;
    }
    
    public PermissaoService getPermissaoService() {
        return permissaoService;
    }
    
    public void setPermissaoService(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }
    
    public Permissao buscarPermissaoCPF(Usuario cpf) {
        return permissaoService.findByCpfUuario(cpf);
    }
    
    public LazyDataModel<Permissao> getList() {
        return list;
    }
    
    public void setList(LazyDataModel<Permissao> list) {
        this.list = list;
    }
    
    public UsuarioService getUsuarioService() {
        return usuarioService;
    }
    
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    public Usuario getUsuarioSelecionado() {
        return UsuarioSelecionado;
    }
    
    public void setUsuarioSelecionado(Usuario UsuarioSelecionado) {
        this.UsuarioSelecionado = UsuarioSelecionado;
    }
    
    public Perfil getPerfil() {
        return perfil;
    }
    
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    public Servidor getServidor() {
        return servidor;
    }
    
    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }
    
    public ServidorService getServidorService() {
        return servidorService;
    }
    
    public void setServidorService(ServidorService servidorService) {
        this.servidorService = servidorService;
    }
    
}
