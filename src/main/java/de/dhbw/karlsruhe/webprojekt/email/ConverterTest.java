/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.karlsruhe.webprojekt.email;

import de.dhbw.karlsruhe.webprojekt.model.Benutzer;
import de.dhbw.karlsruhe.webprojekt.model.Bestellung;
import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import org.apache.fop.apps.FOPException;

/**
 *
 * @author Uwe-Laptop
 */
public class ConverterTest {

    public static void main(String[] args) {
        ObjectConverter converter = new ObjectConverter();
        List<Games> gameListe = new ArrayList<>();
        gameListe.add(new Games("Spiel1"));
        gameListe.add(new Games("Spiel2"));
        Date date = new Date();
        Bestellung bestellung = new Bestellung(0, 0, date, new Benutzer("Test@","", "Test", "TestN", "Eine Addresse"), gameListe);
        converter.convertToXml(bestellung);
        try {
            converter.convertXmlToPdf(123);
        } catch (FOPException ex) {
            Logger.getLogger(ConverterTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ConverterTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConverterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
