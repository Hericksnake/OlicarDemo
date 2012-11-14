/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.beans;

import br.app.teste.security.beans.PapelBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Herick
 */
@Entity
@Table(name = "pessoa")
@NamedQueries({
    @NamedQuery(name = "PessoaBean.findAll", query = "SELECT p FROM PessoaBean p ORDER BY p.nome, p.username"),
    @NamedQuery(name = "PessoaBean.findByNome", query = "SELECT p FROM PessoaBean p WHERE p.nome = :nome"),
    @NamedQuery(name = "PessoaBean.findByUsername", query = "SELECT p FROM PessoaBean p WHERE p.username = :username"),
    @NamedQuery(name = "PessoaBean.findByPassword", query = "SELECT p FROM PessoaBean p WHERE p.password = :password"),
    @NamedQuery(name = "PessoaBean.findByEmail", query = "SELECT p FROM PessoaBean p WHERE p.email = :email")
    })
public class PessoaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "id", sequenceName = "pessoa_id_pessoa_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
    @Column(name = "id_pessoa", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Basic(optional = true)
    @Column(name = "sobrenome", nullable = true, length = 100)
    private String sobrenome;

    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Basic(optional = true)
    @Column(name = "cpf", length = 100)
    private String cpf;

    @Basic(optional = true)
    @Column(name = "rg", length = 100)
    private String rg;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "pessoa_papel",
        joinColumns =
            @JoinColumn(name = "fk_pessoa", referencedColumnName = "id_pessoa"),
        inverseJoinColumns =
            @JoinColumn(name = "fk_papel", referencedColumnName = "id_papel")
    )
    private List<PapelBean> papeis = new ArrayList<PapelBean>();
    
    public PessoaBean() {
    }

    public PessoaBean(Integer id) {
        this.id = id;
    }

    public PessoaBean(Integer id, String nome, String sobrenome, String username, String password, String email, String cpf, String rg) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;        
    }

    public List<PapelBean> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<PapelBean> papeis) {
        this.papeis = papeis;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaBean)) {
            return false;
        }
        PessoaBean other = (PessoaBean) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

}
