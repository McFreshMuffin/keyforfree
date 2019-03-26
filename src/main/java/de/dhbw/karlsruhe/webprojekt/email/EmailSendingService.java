package de.dhbw.karlsruhe.webprojekt.email;

import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSendingService {

    private Session mailSession;
    private MimeMessage mailMessage;
    private MimeBodyPart messageBodyPart;
    Multipart multipart;
    private String from = "service.key4free@gmail.com";

    public EmailSendingService() {
        Properties mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        mailSession = Session.getDefaultInstance(mailServerProperties, null);
    }

    public void setHead(String empfaenger) throws MessagingException {
        mailMessage = new MimeMessage(mailSession);
        mailMessage.setFrom(new InternetAddress(from));
        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(empfaenger));
        mailMessage.setSubject("Rechnung vom " + GregorianCalendar.DAY_OF_MONTH + "/" + GregorianCalendar.MONTH + "/" + GregorianCalendar.YEAR);
    }

    public void setBody(String benutzerNachname) throws MessagingException {
        messageBodyPart = new MimeBodyPart();
        multipart = new MimeMultipart();
        messageBodyPart.setContent(
                "<h1>Sehr geehrter Herr/Frau " + benutzerNachname + "</h1>"
                + "<p>Im folgenden befindet sich ihre Bestellung als PDF, der Key wird noch generiert und Ihnen zugeschickt<p>"
                + "<p>Wir bedanken uns für Ihren Einkauf bei Key4Free<p>"
                + "<p>Mit freundlichen Grüßen,</p>"
                + "<p>Ihr Key4Free-Team<p>", "text/html");
        //messageBodyPart.setText("Das ist der erste Teil vom Body");
        multipart.addBodyPart(messageBodyPart);
    }

    public void setAttachment(long bestellId) throws MessagingException {
        messageBodyPart = new MimeBodyPart();
        String pdf = "C:\\keyforfree\\res\\output\\output" + bestellId + ".pdf";
        DataSource source = new FileDataSource(pdf);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName("Bestellbestätigung Bnr" + bestellId + ".pdf");
        multipart.addBodyPart(messageBodyPart);
    }

    public void createEmail(String empfaenger, String benutzerNachname, long bestellId) {
        try {
            setHead(empfaenger);
            setBody(benutzerNachname);
            setAttachment(bestellId);
            mailMessage.setContent(multipart);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailSendingService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean sendEmail() {
        try (Transport transport = mailSession.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", "service.key4free@gmail.com", "freekeys4u");
            transport.sendMessage(mailMessage, mailMessage.getRecipients(Message.RecipientType.TO));
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(EmailSendingService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
