/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.daos;

import br.app.teste.beans.LocalizacaoBean;
import br.app.teste.config.GenericJpaDao;
import org.apache.log4j.Logger;

/**
 *
 * @author herick
 */
public class LocalizacaoJpaDao extends GenericJpaDao<LocalizacaoBean> {

    private static final Logger logger = Logger.getLogger(AuditoriaJpaDao.class);
    
    public LocalizacaoJpaDao() {
        super(LocalizacaoBean.class);
    }

}
