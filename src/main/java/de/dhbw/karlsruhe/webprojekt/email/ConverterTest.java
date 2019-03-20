/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.karlsruhe.webprojekt.email;

import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.io.IOException;
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
        Games game = new Games(1, "Test");
        converter.convertToXml(game);
        try {
            converter.convertXmlToPdf();
        } catch (FOPException ex) {
            Logger.getLogger(ConverterTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ConverterTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConverterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
