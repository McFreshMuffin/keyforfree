package de.dhbw.karlsruhe.webprojekt.test;

import de.dhbw.karlsruhe.webprojekt.email.ObjectConverter;
import de.dhbw.karlsruhe.webprojekt.model.Benutzer;
import de.dhbw.karlsruhe.webprojekt.model.Bestellung;
import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class XmlTest {

    private ObjectConverter converter;
    private List<Games> gameListe;
    private Bestellung bestellung;

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
            + "    <bestellDatum>2019-03-22T11:02:10.530+01:00</bestellDatum>\n"
            + "    <bestellId>0</bestellId>\n"
            + "    <gameListe>\n"
            + "        <gameId>0</gameId>\n"
            + "        <name>Spiel1</name>\n"
            + "        <releaseDate>0</releaseDate>\n"
            + "        <fsk>0</fsk>\n"
            + "        <price>0.0</price>\n"
            + "    </gameListe>\n"
            + "    <gameListe>\n"
            + "        <gameId>0</gameId>\n"
            + "        <name>Spiel2</name>\n"
            + "        <releaseDate>0</releaseDate>\n"
            + "        <fsk>0</fsk>\n"
            + "        <price>0.0</price>\n"
            + "    </gameListe>\n"
            + "    <gesamtPreis>0.0</gesamtPreis>\n"
            + "</bestellung>";

    @Before

    public void start() {
        converter = new ObjectConverter();
        gameListe = new ArrayList<>();
        gameListe.add(new Games("Spiel1"));
        gameListe.add(new Games("Spiel2"));
        Date date = new Date();
        bestellung = new Bestellung(0, 0, date, new Benutzer("Test@", "", "Test", "TestN", "Eine Addresse"), gameListe);
    }

    @Test
    public void testGeneratedXml() {
        String generatedXml = converter.convertToXml(bestellung);
        assertNotNull("Ist XML null", generatedXml);
    }

    public void testXmlContent() {
        String message = "Fehler in der XML Struktur";
        String generatedXml = converter.convertToXml(bestellung);
        assertEquals(message, generatedXml, expectedXml);
    }

    public void testPdfCreation() {
        //converter.convertXmlToPdf(123);
    }
}
