/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.daos;

import br.app.teste.beans.AuditoriaBean;
import br.app.teste.config.GenericJpaDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author herick
 */
public class AuditoriaJpaDao extends GenericJpaDao<AuditoriaBean> {

    private static final Logger logger = Logger.getLogger(AuditoriaJpaDao.class);
    
    public AuditoriaJpaDao() {
        super(AuditoriaBean.class);
    }

    /**
     * Retorna os resultados ordenados de forma decrescente
     */
    public List<AuditoriaBean> pesquisar() {
        List<AuditoriaBean> auditoria = null;
        EntityManager em = getEntityManager();
        try {            
            Query queryProjetoByKeyword = em.createNamedQuery("AuditoriaBean.findAll");
            auditoria = queryProjetoByKeyword.getResultList();
        } catch (NoResultException nre) {
            logger.debug("Usuário não cadastrado");
        } catch (Exception e) {
            logger.error(e);
        }
        return auditoria;
    }
}
