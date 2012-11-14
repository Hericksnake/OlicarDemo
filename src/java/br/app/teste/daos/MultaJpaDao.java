/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.daos;

import br.app.teste.beans.MultaBean;
import br.app.teste.config.GenericJpaDao;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
public class MultaJpaDao  extends GenericJpaDao<MultaBean> {

    private static final Logger logger = Logger.getLogger(MultaJpaDao.class);
    
    public MultaJpaDao() {
        super(MultaBean.class);
    }
}
