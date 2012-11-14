/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.daos;

import br.app.teste.beans.OrdemServicoBean;
import br.app.teste.config.GenericJpaDao;
import org.apache.log4j.Logger;

/**
 *
 * @author herick
 */
public class OrdemServicoJpaDao extends GenericJpaDao<OrdemServicoBean> {

    private static final Logger logger = Logger.getLogger(AuditoriaJpaDao.class);
    
    public OrdemServicoJpaDao() {
        super(OrdemServicoBean.class);
    }

}
