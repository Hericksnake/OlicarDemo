/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.daos;

import br.app.teste.beans.EmpresaBean;
import br.app.teste.config.GenericJpaDao;
import org.apache.log4j.Logger;

/**
 *
 * @author herick
 */
public class EmpresaJpaDao extends GenericJpaDao<EmpresaBean> {

    private static final Logger logger = Logger.getLogger(EmpresaJpaDao.class);
    
    public EmpresaJpaDao() {
        super(EmpresaBean.class);
    }

}
