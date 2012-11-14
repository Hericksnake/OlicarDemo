/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.jsf;

import br.app.teste.beans.LocalizacaoBean;
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
@ManagedBean(name = "localizacaoMB")
@ViewScoped
public class LocalizacaoManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(LocalizacaoManagedBean.class);
    private List<LocalizacaoBean> listaLocalizacoes = new ArrayList<LocalizacaoBean>();
    private LocalizacaoBean localizacao = new LocalizacaoBean();
    private String pesquisa;
    public static final String ADICIONAR_STATE = "adicionar";
    public static final String EDITAR_STATE = "editar";
    private String currentState = ADICIONAR_STATE;
    private String imagemEditar = "/images/icons/edit_user.png";
    private String imagemDeletar = "/images/icons/editdelete.png";
    private String imagemAdicionar = "/images/icons/apply.png";

    @PostConstruct
    public void postConstruct() {
        listaLocalizacoes = Service.getInstance().listarLocalizacao();
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

    public List<LocalizacaoBean> getListaLocalizacoes() {
        return listaLocalizacoes;
    }

    public void setListaLocalizacoes(List<LocalizacaoBean> listaLocalizacoes) {
        this.listaLocalizacoes = listaLocalizacoes;
    }

    public LocalizacaoBean getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LocalizacaoBean localizacao) {
        this.localizacao = localizacao;
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
        this.localizacao = new LocalizacaoBean();
    }

    public void prepareEditar() {
        this.setCurrentState(this.EDITAR_STATE);
    }

    public void pesquisarLocalizacoes() {
        listaLocalizacoes = Service.getInstance().listarLocalizacao();
    }

    public void gravar() {
        if (ADICIONAR_STATE.equals(this.currentState)) {
            logger.debug("ADICIONAR REGISTRO");
            Service.getInstance().cadastrarLocalizacao(localizacao);

        } else if (EDITAR_STATE.equals(this.currentState)) {
            logger.debug("EDITAR REGISTRO");
            Service.getInstance().atualizarLocalizacao(localizacao);
        }
        this.pesquisarLocalizacoes();
    }

    public void excluir() {
        logger.debug("EXCLUIR REGISTRO");
        Service.getInstance().excluirLocalizacao(localizacao);
        this.pesquisarLocalizacoes();
    }
}
