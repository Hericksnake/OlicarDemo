/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.daos;

import br.app.teste.beans.AetBean;
import br.app.teste.config.GenericJpaDao;
import org.apache.log4j.Logger;

/**
 *
 * @author herick
 */
public class AetJpaDao extends GenericJpaDao<AetBean> {

    private static final Logger logger = Logger.getLogger(AetJpaDao.class);
    
    public AetJpaDao() {
        super(AetBean.class);
    }

}
