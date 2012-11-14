/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author Herick
 */
@Entity
@Table(name = "veiculo")
public class VeiculoBean implements Serializable {
    
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "id_veiculo_gen", sequenceName = "veiculo_id_veiculo_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_veiculo_gen")
    @Column(name = "id_veiculo", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    @JoinColumn(name = "fk_tipo_veiculo", referencedColumnName = "id_tipo_veiculo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoVeiculoBean tipoVeiculo;
        
    @OneToMany(mappedBy = "fkVeiculo", fetch = FetchType.LAZY, cascade=CascadeType.ALL)    
    List<RevisaoBean> revisoes = new ArrayList<RevisaoBean>();
        
    @OneToMany(mappedBy = "fkVeiculo", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    List<MultaBean> multas = new ArrayList<MultaBean>();

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

    public TipoVeiculoBean getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculoBean tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public List<RevisaoBean> getRevisoes() {
        return revisoes;
    }

    public void setRevisoes(List<RevisaoBean> revisoes) {
        this.revisoes = revisoes;
    }

    public List<MultaBean> getMultas() {
        return multas;
    }

    public void setMultas(List<MultaBean> multas) {
        this.multas = multas;
    }
    
        
}
