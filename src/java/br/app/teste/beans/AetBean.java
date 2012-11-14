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
@Table(name = "aet")
public class AetBean implements Serializable {
    
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "id_aet_gen", sequenceName = "aet_id_aet_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_aet_gen")
    @Column(name = "id_aet", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "codigo_aet", nullable = false, length = 100)
    private String codigo;
    //Endereço - CEP, RUA, NUM, BAIRRO, CIDADE, ESTADO
}
