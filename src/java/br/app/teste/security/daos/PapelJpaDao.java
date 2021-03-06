/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.app.teste.security.daos;

import br.app.teste.config.GenericJpaDao;
import br.app.teste.security.beans.PapelBean;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
public class PapelJpaDao extends GenericJpaDao<PapelBean> {

    private static final Logger logger = Logger.getLogger(PapelJpaDao.class);
    
    public PapelJpaDao() {
        super(PapelBean.class);
    }

    public List<PapelBean> pesquisar(String termoPesquisa) {
        List<PapelBean> papeis = null;
        EntityManager em = getEntityManager();
        try {
            Query queryPapelByKeyword = em.createNamedQuery("PapelBean.findByKeyword");
            queryPapelByKeyword.setParameter("nome", "%" + termoPesquisa + "%");
            papeis = queryPapelByKeyword.getResultList();
        } catch (NoResultException nre) {
            logger.debug("Usuário não cadastrado");
        } catch (Exception e) {
            logger.error(e);
        }

        return papeis;
    }

}
