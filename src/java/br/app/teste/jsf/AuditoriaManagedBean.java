/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.jsf;

import br.app.teste.beans.AuditoriaBean;
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
 * @author herick
 */
@ManagedBean(name = "auditoriaMB")
@ViewScoped
public class AuditoriaManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(AuditoriaManagedBean.class);
    private List<AuditoriaBean> listaAuditoria = new ArrayList<AuditoriaBean>();

    @PostConstruct
    public void postConstruct() {
        listaAuditoria = Service.getInstance().listarAuditorias();
    }

    public List<AuditoriaBean> getListaAuditoria() {
        return listaAuditoria;
    }

    public void setListaAuditoria(List<AuditoriaBean> listaAuditoria) {
        this.listaAuditoria = listaAuditoria;
    }
}
