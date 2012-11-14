/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.service;

import br.app.teste.beans.AetBean;
import br.app.teste.beans.AuditoriaBean;
import br.app.teste.beans.EmpresaBean;
import br.app.teste.beans.LocalizacaoBean;
import br.app.teste.beans.MultaBean;
import br.app.teste.beans.OrdemServicoBean;
import br.app.teste.beans.PessoaBean;
import br.app.teste.beans.RevisaoBean;
import br.app.teste.beans.TipoVeiculoBean;
import br.app.teste.beans.VeiculoBean;
import br.app.teste.daos.AetJpaDao;
import br.app.teste.daos.AuditoriaJpaDao;
import br.app.teste.daos.EmpresaJpaDao;
import br.app.teste.daos.LocalizacaoJpaDao;
import br.app.teste.daos.MultaJpaDao;
import br.app.teste.daos.OrdemServicoJpaDao;
import br.app.teste.daos.PessoaJpaDao;
import br.app.teste.daos.RevisaoJpaDao;
import br.app.teste.daos.TipoVeiculoJpaDao;
import br.app.teste.daos.VeiculoJpaDao;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
public class Service {

    private static final Logger logger = Logger.getLogger(Service.class);
    /*
     * Objeto para implementacao do singleton
     */
    private static Service instance = new Service();

    /*
     * Metodo construtor privado, para evitar
     * multipla instanciacao
     */
    private Service() {
    }
    /*
     * Metodo que retorna a unica instancia da classe
     */

    public static Service getInstance() {
        return instance;
    }

    public String encryptPassword(String input) {
        MessageDigest md = null;
        String result = input;
        if (input != null) {
            try {
                md = MessageDigest.getInstance("MD5"); //or "SHA-1"
                md.update(input.getBytes());
                BigInteger hash = new BigInteger(1, md.digest());
                result = hash.toString(16);
            } catch (NoSuchAlgorithmException ex) {
                logger.debug(ex);
            }
            while (result.length() < 32) {
                result = "0" + result;
            }
        }
        return result;
    }

    /*
     * Gera senha randômica da combinação de números e caracteres
     * maíusculas e minúsculas.
     * Parâmetro de passagem é o tamanho da senha a ser gerada
     */
    public String getRandomPassword(int length) {
        char[] ALL_CHARS = new char[62];
        Random RANDOM = new Random();

        for (int i = 48, j = 0; i < 123; i++) {
            if (Character.isLetterOrDigit(i)) {
                ALL_CHARS[j] = (char) i;
                j++;
            }
        }

        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = ALL_CHARS[RANDOM.nextInt(ALL_CHARS.length)];
        }
        return new String(result);
    }

    public PessoaBean realizaLogin(String username, String password) throws Exception {
        if ("".equals(username)) {
            throw new Exception("Username não pode ser vazio");
        }
        if ("".equals(password)) {
            throw new Exception("Senha não pode ser vazia");
        }
        PessoaBean cadastrada = new PessoaJpaDao().findByUsername(username);
        String encryptedPassword = encryptPassword(password);

        if (!encryptedPassword.equals(cadastrada.getPassword())) {
            throw new Exception("Senha inválida");
        }
        return cadastrada;
    }

//-------------------------------- Pessoas ---------------------------------------- 
    public List<PessoaBean> listarPessoas() {
        return new PessoaJpaDao().findEntities();
        //return new PessoaJpaDao().pesquisar();
    }

    public PessoaBean pesquisarPessoaEmail(String email) throws Exception {
        return new PessoaJpaDao().findByEmail(email);
    }

    public List<PessoaBean> pesquisarPessoaCriteria(String termoPesquisa) {
        return new PessoaJpaDao().pesquisarCriteria(termoPesquisa);
    }

    public void cadastrarPessoa(PessoaBean pessoa) {
        new PessoaJpaDao().create(pessoa);
    }

    public void atualizarPessoa(PessoaBean pessoa) {
        new PessoaJpaDao().update(pessoa);
    }

    public void excluirPessoa(PessoaBean pessoa) {
        new PessoaJpaDao().delete(pessoa);
    }

    //-------------------------------- Auditoria ---------------------------------------- 
    public List<AuditoriaBean> listarAuditorias() {
        return new AuditoriaJpaDao().findEntities();
    }

//-------------------------------- Empresas ---------------------------------------- 
    public List<EmpresaBean> listarEmpresas() {
        return new EmpresaJpaDao().findEntities();
    }

    public void cadastrarEmpresa(EmpresaBean empresa) {
        new EmpresaJpaDao().create(empresa);
    }

    public void atualizarEmpresa(EmpresaBean empresa) {
        new EmpresaJpaDao().update(empresa);
    }

    public void excluirEmpresa(EmpresaBean empresa) {
        new EmpresaJpaDao().delete(empresa);
    }
//-------------------------------- Localizacao -------------------------------------

    public List<LocalizacaoBean> listarLocalizacao() {
        return new LocalizacaoJpaDao().findEntities();
    }

    public void cadastrarLocalizacao(LocalizacaoBean local) {
        new LocalizacaoJpaDao().create(local);
    }

    public void atualizarLocalizacao(LocalizacaoBean local) {
        new LocalizacaoJpaDao().update(local);
    }

    public void excluirLocalizacao(LocalizacaoBean local) {
        new LocalizacaoJpaDao().delete(local);
    }
//-------------------------------- Ordem Servico ----------------------------------------     

    public List<OrdemServicoBean> listarOrdemServico() {
        return new OrdemServicoJpaDao().findEntities();
    }

    public void cadastrarOrdemServico(OrdemServicoBean os) {
        new OrdemServicoJpaDao().create(os);
    }

    public void atualizarOrdemServico(OrdemServicoBean os) {
        new OrdemServicoJpaDao().update(os);
    }

    public void excluirOrdemServico(OrdemServicoBean os) {
        new OrdemServicoJpaDao().delete(os);
    }
//-------------------------------- AETs ----------------------------------------     

    public List<AetBean> listarAet() {
        return new AetJpaDao().findEntities();
    }

    public void cadastrarAet(AetBean aet) {
        new AetJpaDao().create(aet);
    }

    public void atualizarAet(AetBean aet) {
        new AetJpaDao().update(aet);
    }

    public void excluirAet(AetBean aet) {
        new AetJpaDao().delete(aet);
    }
    
//-------------------------------- Veiculo ---------------------------------------  

    public VeiculoBean findVeiculoById(Integer id){
        return new VeiculoJpaDao().findEntity(id);
    }
    
    public List<VeiculoBean> listarVeiculo() {
        return new VeiculoJpaDao().findEntities();
    }

    public void cadastrarVeiculo(VeiculoBean veiculo) {
        new VeiculoJpaDao().create(veiculo);
    }

    public VeiculoBean atualizarVeiculo(VeiculoBean veiculo) {
        return new VeiculoJpaDao().update(veiculo);
    }

    public void excluirVeiculo(VeiculoBean veiculo) {
        new VeiculoJpaDao().delete(veiculo);
    }
    
//-------------------------------- Tipo Veiculo ----------------------------------  

    public List<TipoVeiculoBean> listarTipoVeiculo() {
        return new TipoVeiculoJpaDao().findEntities();
    }

    public void cadastrarTipoVeiculo(TipoVeiculoBean tipoVeiculo) {
        new TipoVeiculoJpaDao().create(tipoVeiculo);
    }

    public void atualizarTipoVeiculo(TipoVeiculoBean tipoVeiculo) {
        new TipoVeiculoJpaDao().update(tipoVeiculo);
    }

    public void excluirTipoVeiculo(TipoVeiculoBean tipoVeiculo) {
        new TipoVeiculoJpaDao().delete(tipoVeiculo);
    }
    
//-------------------------------- Multas ----------------------------------------  
    
    public List<MultaBean> listarMultas() {
        return new MultaJpaDao().findEntities();
    }

    public void cadastrarMulta(MultaBean multa) {
        new MultaJpaDao().create(multa);
    }

    public void atualizarMulta(MultaBean multa) {
        new MultaJpaDao().update(multa);
    }

    public void excluirMulta(MultaBean multa) {
        new MultaJpaDao().delete(multa);
    }
    
//-------------------------------- Revisões --------------------------------------  

    public List<RevisaoBean> listarRevisoes() {
        return new RevisaoJpaDao().findEntities();
    }

    public void cadastrarRevisao(RevisaoBean revisao) {
        new RevisaoJpaDao().create(revisao);
    }

    public void atualizarRevisao(RevisaoBean revisao) {
        new RevisaoJpaDao().update(revisao);
    }

    public void excluirRevisao(RevisaoBean revisao) {
        new RevisaoJpaDao().delete(revisao);
    }
    
//-------------------------------- Outros ----------------------------------------  
    
//-------------------------------- Outros ----------------------------------------  
}
