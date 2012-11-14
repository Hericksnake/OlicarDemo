/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.security.jsf.converter;

import br.app.teste.security.beans.PapelBean;
import br.app.teste.security.service.SecurityService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Herick
 */
@FacesConverter(value = "papelConverter")
public class PapelConverter implements Converter {

    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            Integer id = new Integer(string);
            PapelBean papel = SecurityService.getInstance().buscarPapel(id);
            return papel;
        } catch (Exception e) {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        PapelBean papel = (PapelBean) o;
        if (papel != null) {
            return papel.getId().toString();
        } else {
            return null;
        }
    }
}
