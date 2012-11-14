/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.jsf;

import br.app.teste.beans.EmpresaBean;
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
@ManagedBean(name = "empresaMB")
@ViewScoped
public class EmpresaManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(EmpresaManagedBean.class);
    private List<EmpresaBean> listaEmpresas = new ArrayList<EmpresaBean>();
    private EmpresaBean empresa = new EmpresaBean();
    private String pesquisa;
    public static final String ADICIONAR_STATE = "adicionar";
    public static final String EDITAR_STATE = "editar";
    private String currentState = ADICIONAR_STATE;
    private String imagemEditar = "/images/icons/edit_user.png";
    private String imagemDeletar = "/images/icons/editdelete.png";
    private String imagemAdicionar = "/images/icons/apply.png";

    @PostConstruct
    public void postConstruct() {
        listaEmpresas = Service.getInstance().listarEmpresas();
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

    public List<EmpresaBean> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<EmpresaBean> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public EmpresaBean getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaBean empresa) {
        this.empresa = empresa;
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
        this.empresa = new EmpresaBean();
    }

    public void prepareEditar() {
        this.setCurrentState(this.EDITAR_STATE);
    }

    public void pesquisarEmpresas() {
        listaEmpresas = Service.getInstance().listarEmpresas();
    }

    public void gravar() {
        if (ADICIONAR_STATE.equals(this.currentState)) {
            logger.debug("ADICIONAR REGISTRO");
            Service.getInstance().cadastrarEmpresa(empresa);

        } else if (EDITAR_STATE.equals(this.currentState)) {
            logger.debug("EDITAR REGISTRO");
            Service.getInstance().atualizarEmpresa(empresa);
        }
        this.pesquisarEmpresas();
    }

    public void excluir() {
        logger.debug("EXCLUIR REGISTRO");
        Service.getInstance().excluirEmpresa(empresa);
        this.pesquisarEmpresas();
    }
}
