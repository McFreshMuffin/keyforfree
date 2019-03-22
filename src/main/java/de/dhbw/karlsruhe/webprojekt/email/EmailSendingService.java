package de.dhbw.karlsruhe.webprojekt.email;

import java.util.GregorianCalendar;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSendingService {
    
    private Session getMailSession;
    private MimeMessage generateMailMessage;

    public EmailSendingService() {
        Properties mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
    }
    
    public void generateEmail(String empfaenger) throws AddressException, MessagingException{
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(empfaenger));
        generateMailMessage.setSubject("Rechnung vom " +GregorianCalendar.DAY_OF_MONTH+"/"+GregorianCalendar.MONTH+"/"+GregorianCalendar.YEAR);
        String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
        generateMailMessage.setContent(emailBody, "text/html");
    }
    
    public void sendEmail() throws NoSuchProviderException, MessagingException{
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "service.key4free@gmail.com", "freekeys4u");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
