/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Herick
 */
@Entity
@Table(name = "ordem_servico")
public class OrdemServicoBean implements Serializable {

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "id_os_gen", sequenceName = "ordem_servico_id_os_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_os_gen")
    @Column(name = "id_empresa", nullable = false)
    private Integer id;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_os", referencedColumnName = "id_os", nullable = false)
    @OrderBy("codigo")
    List<AetBean> aets = new ArrayList<AetBean>();
    
    @JoinColumn(name = "fk_empresa_pagadora", referencedColumnName = "id_empresa", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EmpresaBean empresaPagadora;
    
    @JoinColumn(name = "fk_empresa_transportadora", referencedColumnName = "id_empresa", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EmpresaBean empresaTransportadora;
    //private int numOrdemServico;
    //Lista Ids da Carga (Fk Carga)
    
    @JoinColumn(name = "fk_local_origem", referencedColumnName = "id_local", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LocalizacaoBean localOrigem;
    
    @JoinColumn(name = "fk_local_destino", referencedColumnName = "id_local", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LocalizacaoBean localDestino;
    //Lista Objetos (Fk Carga)
    //Lista Motoristas Olicar (Fk Motorista)
    
    @Basic(optional = false)
    @Column(name = "numero_er", nullable = false, length = 100)
    private int numeroEr;
    
    @Basic(optional = false)
    @Column(name = "distancia_km", nullable = false, length = 100)
    private int distanciaKm;
    //private float valorCobrado;
    
    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    //Lista de Veículos (Cavalo) (Fk Veiculo)
    //Lista Carretas	(Fk Veiculo)
    //Lista Motoristas Transportadora (Fk Motorista)
    //Documento Olicar (Fk Documento)
    //Status
    
    @Basic(optional = false)
    @Column(name = "observacao", nullable = false, length = 100)
    private String observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    
}
