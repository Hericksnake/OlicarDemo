/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.jsf;

import br.app.teste.beans.OrdemServicoBean;
import br.app.teste.service.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
@ManagedBean(name = "ordemServicoMB")
@ViewScoped
public class OrdemServicoManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(OrdemServicoManagedBean.class);
    private List<OrdemServicoBean> listaOrdensServico = new ArrayList<OrdemServicoBean>();
    private OrdemServicoBean ordemServico = new OrdemServicoBean();
    private String pesquisa;
    public static final String ADICIONAR_STATE = "adicionar";
    public static final String EDITAR_STATE = "editar";
    private String currentState = ADICIONAR_STATE;
    private String imagemEditar = "/images/icons/edit_user.png";
    private String imagemDeletar = "/images/icons/editdelete.png";
    private String imagemAdicionar = "/images/icons/apply.png";

    @PostConstruct
    public void postConstruct() {
        listaOrdensServico = Service.getInstance().listarOrdemServico();
    }

    public String getImagemEditar() {
        return imagemEditar;
    }

    public void setImagemEditar(String imagemEditar) {
        this.imagemEditar = imagemEditar;
    }

    public String getImagemDeletar() {
        return imagemDeletar;
    }

    public void setImagemDeletar(String imagemDeletar) {
        this.imagemDeletar = imagemDeletar;
    }

    public String getImagemAdicionar() {
        return imagemAdicionar;
    }

    public void setImagemAdicionar(String imagemAdicionar) {
        this.imagemAdicionar = imagemAdicionar;
    }

    public List<OrdemServicoBean> getListaOrdensServico() {
        return listaOrdensServico;
    }

    public void setListaOrdensServico(List<OrdemServicoBean> listaOrdensServico) {
        this.listaOrdensServico = listaOrdensServico;
    }

    public OrdemServicoBean getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServicoBean ordemServico) {
        this.ordemServico = ordemServico;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public void prepareAdicionar() {
        this.setCurrentState(this.ADICIONAR_STATE);
        this.ordemServico = new OrdemServicoBean();
    }

    public void prepareEditar() {
        this.setCurrentState(this.EDITAR_STATE);
    }

    public void pesquisarOrdemServico() {
        listaOrdensServico = Service.getInstance().listarOrdemServico();
    }
    
    public void gravar() {
        if (ADICIONAR_STATE.equals(this.currentState)) {
            logger.debug("ADICIONAR REGISTRO");
            Service.getInstance().cadastrarOrdemServico(ordemServico);

        } else if (EDITAR_STATE.equals(this.currentState)) {
            logger.debug("EDITAR REGISTRO");
            Service.getInstance().atualizarOrdemServico(ordemServico);
        }
        this.pesquisarOrdemServico();
    }

    public void excluir() {
        logger.debug("EXCLUIR REGISTRO");
        Service.getInstance().excluirOrdemServico(ordemServico);
        this.pesquisarOrdemServico();
    }
}
