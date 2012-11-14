/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.security.jsf.converter;

import br.app.teste.security.beans.ObjetoProtegidoBean;
import br.app.teste.security.service.SecurityService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Herick
 */
@FacesConverter(value = "objetoProtegidoConverter")
public class ObjetoProtegidoConverter implements Converter {

    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        try {
            Integer id = Integer.parseInt(value);
            ObjetoProtegidoBean objetoP = SecurityService.getInstance().getObjetoProtegido(id);
            return objetoP;
        } catch (Exception e) {
            return null;
        }
    }

    public String getAsString(FacesContext ctx, UIComponent component, Object value) {
        if (value != null) {
            ObjetoProtegidoBean objetoP = (ObjetoProtegidoBean) value;
            return objetoP.getId().toString();
        } else {
            return null;
        }
    }
}
