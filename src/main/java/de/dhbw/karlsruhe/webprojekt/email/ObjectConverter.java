/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.karlsruhe.webprojekt.email;

import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Uwe-Laptop
 */
public class ObjectConverter {

    public void convertToXml(Games game) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Games.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(game, sw);
            String xmlContent = sw.toString();
            System.out.println(xmlContent);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
