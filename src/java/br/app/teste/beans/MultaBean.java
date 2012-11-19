package br.app.teste.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Herick
 */
@Entity
@Table(name = "multa")
public class MultaBean implements Serializable {
    
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "id_multa_gen", sequenceName = "multa_id_multa_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_multa_gen")
    @Column(name = "id_multa", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    //@ManyToOne
    //@JoinColumn(name = "fk_veiculo")
    @JoinColumn(name = "fk_veiculo", referencedColumnName = "id_veiculo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VeiculoBean fkVeiculo;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VeiculoBean getFkVeiculo() {
        return fkVeiculo;
    }

    public void setFkVeiculo(VeiculoBean fkVeiculo) {
        this.fkVeiculo = fkVeiculo;
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
        final MultaBean other = (MultaBean) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }    
}
