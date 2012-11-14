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
@Table(name = "tipo_veiculo")
public class TipoVeiculoBean implements Serializable {
    
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "id_tipoVeic_gen", sequenceName = "tipo_veiculo_id_tipo_veiculo_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_tipoVeic_gen")
    @Column(name = "id_tipo_veiculo", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoVeiculoBean other = (TipoVeiculoBean) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    
}
