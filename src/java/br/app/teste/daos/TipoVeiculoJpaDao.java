/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.daos;

import br.app.teste.beans.TipoVeiculoBean;
import br.app.teste.config.GenericJpaDao;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
public class TipoVeiculoJpaDao  extends GenericJpaDao<TipoVeiculoBean> {

    private static final Logger logger = Logger.getLogger(TipoVeiculoJpaDao.class);
    
    public TipoVeiculoJpaDao() {
        super(TipoVeiculoBean.class);
    }
    
}
