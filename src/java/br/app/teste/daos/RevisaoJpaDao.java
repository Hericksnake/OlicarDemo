/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.daos;

import br.app.teste.beans.RevisaoBean;
import br.app.teste.config.GenericJpaDao;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
public class RevisaoJpaDao  extends GenericJpaDao<RevisaoBean> {

    private static final Logger logger = Logger.getLogger(RevisaoJpaDao.class);
    
    public RevisaoJpaDao() {
        super(RevisaoBean.class);
    }
    
}
