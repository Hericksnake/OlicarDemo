/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.service;

import br.app.teste.beans.AuditoriaBean;
import br.app.teste.beans.PessoaBean;
import br.app.teste.daos.AuditoriaJpaDao;
import java.util.Date;
import java.util.TimeZone;
import org.apache.log4j.Logger;

/**
 *
 * @author herick
 */
public class AuditoriaService {

    private static AuditoriaService instance = new AuditoriaService();
    Logger logger = Logger.getLogger(this.getClass());

    private AuditoriaService() {
    }

    public static AuditoriaService getInstance() {
        return instance;
    }

    public void gravarAcaoUsuario(PessoaBean pessoa, String acao, String modulo, String descricao) {
        int id = pessoa.getId();
        String nomePessoa = pessoa.getNome();
        String username = pessoa.getUsername();

        gravarAcao(acao, modulo, id, nomePessoa, username, descricao);
    }

    public void gravarAcao(String acao, String modulo, int idPessoa, String nomePessoa, String username, String descricao) {

        AuditoriaBean auditoria = new AuditoriaBean();
        TimeZone tz = TimeZone.getDefault();
        Date data = new Date();
        logger.debug("Data auditoria --- > " + new Date(data.getTime() + tz.getRawOffset()));

        if (descricao == null) {
            descricao = "";
        }

        auditoria.setAcao(acao);
        auditoria.setModulo(modulo);
        auditoria.setDataAcao(new Date(data.getTime() + tz.getRawOffset()));
        auditoria.setIdPessoa(idPessoa);
        auditoria.setNomePessoa(nomePessoa);
        auditoria.setUsername(username);
        auditoria.setDescricao(descricao);

        new AuditoriaJpaDao().create(auditoria);
        logger.debug("Auditoria executada");
    }
}
