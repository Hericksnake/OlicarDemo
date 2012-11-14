/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.jsf;

import br.app.teste.beans.PessoaBean;
import br.app.teste.security.beans.PapelBean;
import br.app.teste.security.service.SecurityService;
import br.app.teste.service.ReportService;
import br.app.teste.service.Service;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Herick
 */
@ManagedBean(name = "pessoaMB")
@ViewScoped
public class PessoaManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(PessoaManagedBean.class);
    private List<PessoaBean> listaPessoas = new ArrayList<PessoaBean>();
    private PessoaBean pessoa = new PessoaBean();
    private String pesquisa;
    public static final String ADICIONAR_STATE = "adicionar";
    public static final String EDITAR_STATE = "editar";
    private String currentState = ADICIONAR_STATE;
    private DualListModel<PapelBean> listaGrupos = new DualListModel<PapelBean>();

    @PostConstruct
    public void postConstruct() {
        listaPessoas = Service.getInstance().listarPessoas();
    }

    public List<PessoaBean> getListaPessoas() {
        return listaPessoas;
    }

    public void setListaPessoas(List<PessoaBean> listaPessoas) {
        this.listaPessoas = listaPessoas;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public PessoaBean getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaBean pessoa) {
        this.pessoa = pessoa;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public DualListModel<PapelBean> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(DualListModel<PapelBean> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public void prepareAdicionar() {
        this.setCurrentState(this.ADICIONAR_STATE);
        this.pessoa = new PessoaBean();
        List<PapelBean> listaTodosPapeis = new ArrayList<PapelBean>();
        listaTodosPapeis = SecurityService.getInstance().listarPapeis();
        listaGrupos = new DualListModel<PapelBean>(listaTodosPapeis, new ArrayList<PapelBean>());
    }

    public void prepareEditar() {
        this.setCurrentState(this.EDITAR_STATE);
        List<PapelBean> listaTodosPapeis = new ArrayList<PapelBean>();
        listaTodosPapeis = SecurityService.getInstance().listarPapeis();
        listaTodosPapeis.removeAll(pessoa.getPapeis());
        List<PapelBean> listaPapeisPessoa = new ArrayList<PapelBean>();
        listaPapeisPessoa.addAll(pessoa.getPapeis());
        listaGrupos = new DualListModel<PapelBean>(listaTodosPapeis, listaPapeisPessoa);
    }

    public void pesquisarPessoas() {
        listaPessoas = Service.getInstance().pesquisarPessoaCriteria(pesquisa);
    }

    public void detalharPessoa(PessoaBean pessoa) {
        logger.debug(pessoa.getNome());
    }

    public void gravar() {
        if (ADICIONAR_STATE.equals(this.currentState)) {
            logger.debug("ADICIONAR REGISTRO");

            String senha = Service.getInstance().getRandomPassword(8);
            pessoa.setPassword(Service.getInstance().encryptPassword(senha));
            Service.getInstance().cadastrarPessoa(pessoa);
            pessoa.getPapeis().addAll(listaGrupos.getTarget());
            Service.getInstance().atualizarPessoa(pessoa);

        } else if (EDITAR_STATE.equals(this.currentState)) {
            logger.debug("EDITAR REGISTRO");
            pessoa.getPapeis().removeAll(listaGrupos.getSource());
            listaGrupos.getTarget().removeAll(pessoa.getPapeis());
            pessoa.getPapeis().addAll(listaGrupos.getTarget());
            Service.getInstance().atualizarPessoa(pessoa);
        }
        this.pesquisarPessoas();
    }

    public void excluir() {
        logger.debug("EXCLUIR REGISTRO");
        Service.getInstance().excluirPessoa(pessoa);
        this.pesquisarPessoas();
    }

    public StreamedContent downloadReportPdf() {
        logger.debug("GERAR RELATORIO PDF");
        InputStream stream = ReportService.getInstance().emiteRelatorioPessoas(listaPessoas, ReportService.FORMATO_PDF);
        StreamedContent file = new DefaultStreamedContent(stream, "application/pdf", "report.pdf");
        return file;
    }
}
