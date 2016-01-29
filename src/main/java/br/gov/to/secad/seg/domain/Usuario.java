/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Classe que implementa as informações de acesso ao portal do servidor,
 * implementa a classe UserDetails, que é usada para autenticação.
 *
 * @author wellyngton.santos
 */
@Entity
@Table(name = "seg_usuario", schema = "seg")
public class Usuario implements Serializable, UserDetails {

    /**
     * Atributo identificador da classe
     */
    @Id
    private Integer id;
    /**
     * Atributo cpf - guarda o cpf do servidor, também utilizado como chave de
     * usuário.
     */
    @Column
    private String cpf;
    /**
     * Atributo email - guarda o email do servidor, informação necessária para
     * recuperação de senha
     */
    @Column
    private String email;
    /**
     * Atributo senhahash - guarda, de forma criptografada, a informação da
     * senha de acesso ao portal do servidor.
     */
    @Column
    private String senhahash;
    /**
     * Lista roles - guarda a lista de grupos de usuário a qual o usuário está
     * vinculado.
     *
     * @ManyToMany(fetch = FetchType.EAGER)
     * @JoinTable(name = "permissao_usuario", schema = "aede", joinColumns = {
     * @JoinColumn(name = "cpf", table = "seg.seg_usuario", referencedColumnName
     * = "cpf")}, inverseJoinColumns = @JoinColumn(name = "grupo", table =
     * "grupo_usuario", referencedColumnName = "id") ) private List<Role> roles;
     */

    @OneToMany(mappedBy = "usuario")
    private List<Permissao> permissoes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhahash() {
        return senhahash;
    }

    public void setSenhahash(String senhahash) {
        this.senhahash = senhahash;
    }

    /*
     public String getRolePrincipal(){
     return roles.get(0).getAuthority();
     }
     public List<Role> getRoles(){
     return roles;
     }/*
     */
    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    /**
     * Método da classe UserDetails que retorna a lista de autorizações do
     * usuário, ou seja, a quais grupos ele pertence.
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> result = new ArrayList<>();
        for (Permissao permissao : permissoes) {
            result.add(new GrantedAuthorityImpl(permissao.getPerfil().getDescricao()));
        }//To change body of generated methods, choose Tools | Templates.
        return result;
    }

    /**
     * Método da classe UserDetails que retorna a senha do usuário.
     *
     * @return
     */
    @Override
    public String getPassword() {
        return senhahash; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método da classe UserDetails que retorna nome que o usuário usa para
     * efetuar login, neste caso retornando o cpf.
     *
     * @return
     */
    @Override
    public String getUsername() {
        return cpf; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método da classe UserDetails que verifica se a conta do usuário não
     * expirou, neste caso, considera-se que o usuário nunca terá sua conta
     * expirada, sendo assim, retorna-se true sempre.
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método da classe UserDetails que verifica se a conta do usuário não está
     * bloqueada, neste caso, ainda não foi definida uma situação que bloquei o
     * acesso do usuário então retorna-se true.
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método da classe UserDetails que verifica se a conta do usuário não
     * expirou, neste caso, considera-se que o usuário nunca terá sua conta
     * expirada, sendo assim, retorna-se true sempre.
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método da classe UserDetails que verifica se a conta do usuário está
     * ativa.
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

}
