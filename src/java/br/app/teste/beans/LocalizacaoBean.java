/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Herick
 */
@Entity
@Table(name = "localizacao")
public class LocalizacaoBean implements Serializable {
    
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "id_local_gen", sequenceName = "localizacao_id_local_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_local_gen")
    @Column(name = "id_local", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    //Endereço - CEP, RUA, NUM, BAIRRO, CIDADE, ESTADO

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
