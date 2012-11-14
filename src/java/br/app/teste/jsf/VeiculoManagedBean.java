/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.jsf;

import br.app.teste.beans.MultaBean;
import br.app.teste.beans.RevisaoBean;
import br.app.teste.beans.TipoVeiculoBean;
import br.app.teste.beans.VeiculoBean;
import br.app.teste.service.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
@ManagedBean(name = "veiculoMB")
@ViewScoped
public class VeiculoManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(VeiculoManagedBean.class);
    private List<VeiculoBean> listaVeiculos = new ArrayList<VeiculoBean>();
    private VeiculoBean veiculo = new VeiculoBean();
    private String pesquisa;
    public static final String ADICIONAR_STATE = "adicionar";
    public static final String EDITAR_STATE = "editar";
    private String currentState = ADICIONAR_STATE;
    private String imagemEditar = "/images/icons/edit_user.png";
    private String imagemDeletar = "/images/icons/editdelete.png";
    private String imagemAdicionar = "/images/icons/apply.png";
    private String imagemInfo = "/images/icons/info.png";
    private String imagemAddMulta = "/images/icons/multa.png";
    private String imagemAddRevisao = "/images/icons/configure.png";
    private List<TipoVeiculoBean> listaTiposVeiculos = new ArrayList<TipoVeiculoBean>();
    private TipoVeiculoBean tipoVeiculo = new TipoVeiculoBean();
    private List<SelectItem> listaTiposVeiculosItems = new ArrayList<SelectItem>();
    private int idTipoVeiculoSelecionado;
    private MultaBean multa = new MultaBean();
    private RevisaoBean revisao = new RevisaoBean();

    @PostConstruct
    public void postConstruct() {
        listaVeiculos = Service.getInstance().listarVeiculo();

        listaTiposVeiculos = Service.getInstance().listarTipoVeiculo();
        for (TipoVeiculoBean tipo : listaTiposVeiculos) {
            this.getListaTiposVeiculosItems().add(new SelectItem(tipo.getId(), tipo.getNome()));
        }
    }

    public String getImagemInfo() {
        return imagemInfo;
    }

    public void setImagemInfo(String imagemInfo) {
        this.imagemInfo = imagemInfo;
    }

    public String getImagemAddMulta() {
        return imagemAddMulta;
    }

    public void setImagemAddMulta(String imagemAddMulta) {
        this.imagemAddMulta = imagemAddMulta;
    }

    public String getImagemAddRevisao() {
        return imagemAddRevisao;
    }

    public void setImagemAddRevisao(String imagemAddRevisao) {
        this.imagemAddRevisao = imagemAddRevisao;
    }

    public MultaBean getMulta() {
        return multa;
    }

    public void setMulta(MultaBean multa) {
        this.multa = multa;
    }

    public RevisaoBean getRevisao() {
        return revisao;
    }

    public void setRevisao(RevisaoBean revisao) {
        this.revisao = revisao;
    }

    public List<TipoVeiculoBean> getListaTiposVeiculos() {
        return listaTiposVeiculos;
    }

    public void setListaTiposVeiculos(List<TipoVeiculoBean> listaTiposVeiculos) {
        this.listaTiposVeiculos = listaTiposVeiculos;
    }

    public TipoVeiculoBean getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculoBean tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public List<SelectItem> getListaTiposVeiculosItems() {
        return listaTiposVeiculosItems;
    }

    public void setListaTiposVeiculosItems(List<SelectItem> listaTiposVeiculosItems) {
        this.listaTiposVeiculosItems = listaTiposVeiculosItems;
    }

    public int getIdTipoVeiculoSelecionado() {
        return idTipoVeiculoSelecionado;
    }

    public void setIdTipoVeiculoSelecionado(int idTipoVeiculoSelecionado) {
        this.idTipoVeiculoSelecionado = idTipoVeiculoSelecionado;
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

    public List<VeiculoBean> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<VeiculoBean> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public VeiculoBean getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoBean veiculo) {
        this.veiculo = veiculo;
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
        this.veiculo = new VeiculoBean();
    }

    public void prepareEditar() {
        this.setCurrentState(this.EDITAR_STATE);
        idTipoVeiculoSelecionado = veiculo.getTipoVeiculo().getId();
    }

    public void pesquisarVeiculos() {
        listaVeiculos = Service.getInstance().listarVeiculo();
    }

    public void gravar() {

        if (ADICIONAR_STATE.equals(this.currentState)) {
            logger.debug("ADICIONAR REGISTRO");
            TipoVeiculoSelecionado();
            Service.getInstance().cadastrarVeiculo(veiculo);

        } else if (EDITAR_STATE.equals(this.currentState)) {
            logger.debug("EDITAR REGISTRO");
            TipoVeiculoSelecionado();;
            Service.getInstance().atualizarVeiculo(veiculo);
        }
        this.pesquisarVeiculos();
    }

    public void excluir() {
        logger.debug("EXCLUIR REGISTRO");
        Service.getInstance().excluirVeiculo(veiculo);
        this.pesquisarVeiculos();
    }

    public void TipoVeiculoSelecionado() {
        TipoVeiculoBean tipoSelecionado = new TipoVeiculoBean();
        tipoSelecionado.setId(getIdTipoVeiculoSelecionado());
        if (getListaTiposVeiculos().contains(tipoSelecionado)) {
            int idx = getListaTiposVeiculos().indexOf(tipoSelecionado);
            veiculo.setTipoVeiculo(getListaTiposVeiculos().get(idx));
        }
    }

    public void prepareAddMulta() {
        multa = new MultaBean();
    }

    public void prepareAddRevisao() {
        revisao = new RevisaoBean();
    }

    public void gravarMulta() {
        logger.debug("ADICIONAR Multa");
        multa.setFkVeiculo(veiculo);
        veiculo.getMultas().add(multa);
        Service.getInstance().atualizarVeiculo(veiculo);
        pesquisarVeiculos();
    }

    public void gravarRevisao() {
        logger.debug("ADICIONAR Revisão");
        revisao.setFkVeiculo(veiculo);
        veiculo.getRevisoes().add(revisao);
        Service.getInstance().atualizarVeiculo(veiculo);
        pesquisarVeiculos();
        
    }

    public void excluirMulta() {
        logger.debug("Excluir Multa");
        veiculo.getMultas().remove(multa);
        Service.getInstance().excluirMulta(multa);        
        veiculo = Service.getInstance().atualizarVeiculo(veiculo);
        pesquisarVeiculos();
    }

    public void excluirRevisao() {
        logger.debug("Excluir Revisao");
        veiculo.getRevisoes().remove(revisao);
        Service.getInstance().excluirRevisao(revisao);
        veiculo = Service.getInstance().atualizarVeiculo(veiculo);
        pesquisarVeiculos();
    }
}
