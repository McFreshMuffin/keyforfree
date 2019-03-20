/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.karlsruhe.webprojekt.email;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author Uwe-Laptop
 */
public class EmailTester {

    public static void main(String[] args) {
        try {
            EmailSendingService emailSendingService = new EmailSendingService();
            emailSendingService.generateEmail("service.key4free@gmail.com");
            emailSendingService.sendEmail();
        } catch (MessagingException ex) {
            Logger.getLogger(EmailTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
