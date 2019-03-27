package de.dhbw.karlsruhe.webprojekt.test;

import de.dhbw.karlsruhe.webprojekt.email.EmailSendingService;
import de.dhbw.karlsruhe.webprojekt.model.Benutzer;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class EmailTest {

    private EmailSendingService ess;
    private Benutzer benutzer;
    
    @Before
    public void initEss() {
        ess = new EmailSendingService();
        benutzer = new Benutzer("service.key4free@gmail.com", "", "TestUser", "TestUser", "TestAddresse");
    }

    @Test
    @Ignore
    public void testEmailSending() {
        ess.createEmail(benutzer.getEmail(), benutzer.getNachname(), 0);
        boolean result = ess.sendEmail();
        assertTrue(result);
    }
}
