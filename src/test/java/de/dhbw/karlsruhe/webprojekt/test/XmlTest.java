package de.dhbw.karlsruhe.webprojekt.test;

import de.dhbw.karlsruhe.webprojekt.email.ObjectConverter;
import de.dhbw.karlsruhe.webprojekt.model.Benutzer;
import de.dhbw.karlsruhe.webprojekt.model.Bestellung;
import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class XmlTest {

    private ObjectConverter converter;
    private List<Games> gameListe;
    private Bestellung bestellung;
    String generatedXml;

    private String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
            + "<bestellung>\n"
            + "    <benutzer>\n"
            + "        <benutzerId>0</benutzerId>\n"
            + "        <email>Test@</email>\n"
            + "        <password></password>\n"
            + "        <vorname>Test</vorname>\n"
            + "        <nachname>TestN</nachname>\n"
            + "        <addresse>Eine Addresse</addresse>\n"
            + "    </benutzer>\n"
            + "    <benutzerId>0</benutzerId>\n"
            + "    <bestellDatum>2014-03-11T00:00:00+01:00</bestellDatum>\n"
            + "    <bestellId>0</bestellId>\n"
            + "    <gameListe>\n"
            + "        <gameId>0</gameId>\n"
            + "        <name>Spiel1</name>\n"
            + "        <releaseDate>0</releaseDate>\n"
            + "        <fsk>0</fsk>\n"
            + "        <price>0.0</price>\n"
            + "        <image>http://cdn.akamai.steamstatic.com/steam/apps/327680/header.jpg?t=1469216185</image>\n"
            + "    </gameListe>\n"
            + "    <gameListe>\n"
            + "        <gameId>0</gameId>\n"
            + "        <name>Spiel2</name>\n"
            + "        <releaseDate>0</releaseDate>\n"
            + "        <fsk>0</fsk>\n"
            + "        <price>0.0</price>\n"
            + "        <image>http://cdn.akamai.steamstatic.com/steam/apps/327680/header.jpg?t=1469216185</image>\n"
            + "    </gameListe>\n"
            + "    <gesamtPreis>0.0</gesamtPreis>\n"
            + "</bestellung>";

    @Before

    public void start() {
        converter = new ObjectConverter();
        gameListe = new ArrayList<>();
        gameListe.add(new Games("Spiel1", "http://cdn.akamai.steamstatic.com/steam/apps/327680/header.jpg?t=1469216185"));
        gameListe.add(new Games("Spiel2", "http://cdn.akamai.steamstatic.com/steam/apps/327680/header.jpg?t=1469216185"));
        Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
        Date date = myCalendar.getTime();
        bestellung = new Bestellung(0, 0, date, new Benutzer("Test@", "", "Test", "TestN", "Eine Addresse"), gameListe);
    }

    @Test
    public void testGeneratedXml() {
        generatedXml = converter.convertToXml(bestellung);
        System.out.println(generatedXml);
        assertNotNull("Ist XML null", generatedXml);
        //String message = "Fehler in der XML Struktur";
        //assertEquals(message, generatedXml, expectedXml);
        converter.convertXmlToPdf(123);
    }
}
