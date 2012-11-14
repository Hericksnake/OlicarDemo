/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.teste.service;

import org.apache.log4j.Logger;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Herick
 */
public class MailService {

    private static MailService instance = new MailService();
    Logger logger = Logger.getLogger(this.getClass());
    private String username; // = "seu-email@gmail.com";
    private String password; // = "sua-senha";
    private String host; // = "smtp.gmail.com";
    /*
     * Metodo construtor privado, para evitar multipla instanciacao
     */

    private MailService() {
        lerPropertiesMail();
    }

    public static MailService getInstance() {
        return instance;
    }

    /**
     * Send mails to
     *
     * @param subject
     * @param sendTo
     * @param message
     */
    public void sendMail(final String subject, final String sendTo, final String message) {
        Thread sendMail = new Thread() {

            @Override
            public void run() {
                Properties p = new Properties();
                p.put("mail.smtp.host", host);
                p.put("mail.smtp.starttls.enable", "true");
                p.put("mail.smtp.auth", "true");

                Authenticator auth = new SMTPAuthenticator();

                Session session = Session.getInstance(p, auth);
                MimeMessage msg = new MimeMessage(session);

                try {
                    // "de" e "para"!!
                    msg.setFrom(new InternetAddress(username));
                    msg.setRecipients(Message.RecipientType.TO, sendTo);

                    msg.setSentDate(new Date());
                    msg.setSubject(subject);
                    msg.setText(message);
                    //Para envio de HTML
                    //msg.setContent(message, "text/html");

                    // enviando mensagem (tentando)
                    Transport.send(msg);
                    logger.debug("Email Enviado com Sucesso!!!");
                } catch (AddressException e) {
                    // nunca deixe catches vazios!
                    logger.debug("Address Exception - " + e.getMessage());
                } catch (MessagingException e) {
                    // nunca deixe catches vazios!
                    logger.debug("Messaging Exception - " + e.getMessage());
                }
            }
        };
        sendMail.start();
    }

    private class SMTPAuthenticator extends Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }

    private void lerPropertiesMail() {
        ResourceBundle bundle = ResourceBundle.getBundle("./mail");
        host = bundle.getString("mail.host");
        username = bundle.getString("mail.username");
        password = bundle.getString("mail.password");
    }
}
