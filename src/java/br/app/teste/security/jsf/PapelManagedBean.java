/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.security.jsf;

import br.app.teste.jsf.UserSessionManagedBean;
import br.app.teste.security.RbacConstantes;
import br.app.teste.security.beans.ObjetoProtegidoBean;
import br.app.teste.security.beans.PapelBean;
import br.app.teste.security.service.SecurityService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Herick
 */
@ManagedBean(name = "papelMB")
@ViewScoped
public class PapelManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(PapelManagedBean.class);
    private List<PapelBean> listaPapeis = new ArrayList<PapelBean>();
    private PapelBean papel = new PapelBean();
    private String pesquisa;
    public static final String ADICIONAR_STATE = "adicionar";
    public static final String EDITAR_STATE = "editar";
    private String currentState = ADICIONAR_STATE;
    private DualListModel<ObjetoProtegidoBean> objetosProtegidosDisponiveis;    
    @ManagedProperty(value = "#{userSessionMB}")
    private UserSessionManagedBean userSessionMB;
    private boolean criarEditarExcluirPapel = true;
    private String imagemEditar = "/images/icons/edit_user.png";
    private String imagemDeletar = "/images/icons/editdelete.png";
    private String imagemAdicionar = "/images/icons/apply.png";
    
    @PostConstruct
    public void postConstruct() {
        criarEditarExcluirPapel = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.CRIA_EDITA_PAPEIS);
        listaPapeis = SecurityService.getInstance().listarPapeis();
        objetosProtegidosDisponiveis = new DualListModel<ObjetoProtegidoBean>();
    }

     public String getImagemAdicionar() {
        if (criarEditarExcluirPapel) {
            imagemAdicionar = "/images/icons/apply.png";
        } else {
            imagemAdicionar = "/images/icons/applybw.png";
        }
        return imagemAdicionar;
    }

    public void setImagemAdicionar(String imagemAdicionar) {
        this.imagemAdicionar = imagemAdicionar;
    }

    public String getImagemDeletar() {
        if (criarEditarExcluirPapel) {
            imagemDeletar = "/images/icons/editdelete.png";
        } else {
            imagemDeletar = "/images/icons/editdeletebw.png";
        }
        return imagemDeletar;
    }

    public void setImagemDeletar(String imagemDeletar) {
        this.imagemDeletar = imagemDeletar;
    }

    public String getImagemEditar() {
        if (criarEditarExcluirPapel) {
            imagemEditar = "/images/icons/edit_user.png";
        } else {
            imagemEditar = "/images/icons/edit_userbw.png";
        }
        return imagemEditar;
    }

    public void setImagemEditar(String imagemEditar) {
        this.imagemEditar = imagemEditar;
    }
    
    
    public boolean isCriarEditarExcluirPapel() {
        return criarEditarExcluirPapel;
    }

    public void setCriarEditarExcluirPapel(boolean criarEditarExcluirPapel) {
        this.criarEditarExcluirPapel = criarEditarExcluirPapel;
    }

    public UserSessionManagedBean getUserSessionMB() {
        return userSessionMB;
    }

    public void setUserSessionMB(UserSessionManagedBean userSessionMB) {
        this.userSessionMB = userSessionMB;
    }

    public List<PapelBean> getListaPapeis() {
        return listaPapeis;
    }

    public void setListaPapeis(List<PapelBean> listaPapeis) {
        this.listaPapeis = listaPapeis;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public PapelBean getPapel() {
        return papel;
    }

    public void setPapel(PapelBean papel) {
        this.papel = papel;
    }

    public DualListModel<ObjetoProtegidoBean> getObjetosProtegidosDisponiveis() {
        return objetosProtegidosDisponiveis;
    }

    public void setObjetosProtegidosDisponiveis(DualListModel<ObjetoProtegidoBean> objetosProtegidosDisponiveis) {
        this.objetosProtegidosDisponiveis = objetosProtegidosDisponiveis;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public void prepareAdicionar() {
        this.setCurrentState(this.ADICIONAR_STATE);
        this.papel = new PapelBean();
        List<ObjetoProtegidoBean> todosObjetosProtegidos = SecurityService.getInstance().listarObjetosProtegidos();
        objetosProtegidosDisponiveis = new DualListModel<ObjetoProtegidoBean>(todosObjetosProtegidos, papel.getObjetosProtegidos());
    }

    public void prepareEditar() {
        this.setCurrentState(this.EDITAR_STATE);
        List<ObjetoProtegidoBean> todosObjetosProtegidos = SecurityService.getInstance().listarObjetosProtegidos();
        todosObjetosProtegidos.removeAll(papel.getObjetosProtegidos());
        objetosProtegidosDisponiveis = new DualListModel<ObjetoProtegidoBean>(todosObjetosProtegidos, papel.getObjetosProtegidos());
    }

    public void pesquisarPapeis() {
        listaPapeis = SecurityService.getInstance().pesquisarPapel(pesquisa);
    }

    public void gravar() {
        if (ADICIONAR_STATE.equals(this.currentState)) {
            logger.debug("ADICIONAR REGISTRO");
            SecurityService.getInstance().cadastraPapel(papel);
            papel.setObjetosProtegidos(objetosProtegidosDisponiveis.getTarget());
            SecurityService.getInstance().atualizarPapel(papel);
        } else if (EDITAR_STATE.equals(this.currentState)) {
            logger.debug("EDITAR REGISTRO");
            papel.setObjetosProtegidos(objetosProtegidosDisponiveis.getTarget());
            SecurityService.getInstance().atualizarPapel(papel);
        }
        listaPapeis = SecurityService.getInstance().listarPapeis();
    }

    public void excluir() {
        logger.debug("EXCLUIR REGISTRO");
        SecurityService.getInstance().excluirPapel(papel);
        this.pesquisarPapeis();
    }
}
