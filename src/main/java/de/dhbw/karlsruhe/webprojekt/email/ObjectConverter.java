package de.dhbw.karlsruhe.webprojekt.email;

import de.dhbw.karlsruhe.webprojekt.model.Bestellung;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class ObjectConverter {

    private final String RESOURCES_DIR = "C:\\keyforfree\\res\\template";
    private final String OUTPUT_DIR = "C:\\keyforfree\\res\\output";

    private StringBuilder xmlBuilder = new StringBuilder();

    public String convertToXml(Bestellung bestellung) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bestellung.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(bestellung, sw);
            xmlBuilder.append(sw.toString());
            return xmlBuilder.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void convertXmlToPdf(long bestellId) {
        File xsltFile = new File(RESOURCES_DIR + "//template.xsl");
        StreamSource xmlSource = new StreamSource(new StringReader(xmlBuilder.toString()));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        try (OutputStream out = new FileOutputStream(OUTPUT_DIR + "//output" + bestellId + ".pdf")) {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        } catch (FOPException | TransformerException | IOException ex) {
            Logger.getLogger(ObjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
