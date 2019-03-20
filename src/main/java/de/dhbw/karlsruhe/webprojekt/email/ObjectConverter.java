package de.dhbw.karlsruhe.webprojekt.email;

import de.dhbw.karlsruhe.webprojekt.model.Bestellung;
import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
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
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.xalan.processor.TransformerFactoryImpl;

public class ObjectConverter {

    /*
    private final String template = "src//main//resources//template.xsl";
    private final String output = "src//main//resources//output//result.pdf";
    private StringBuilder xml = new StringBuilder();
    
    
    public void convertToXml(Games game){
        StringWriter sw = new StringWriter();
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Games.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(game, sw);
            xml.append(sw.toString());
            System.out.println("XML --->\n" + xml.toString());
        } catch (JAXBException ex) {
            Logger.getLogger(ObjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void convert(Games game) throws JAXBException, FileNotFoundException, IOException, TransformerException, FOPException {
        File xsltfile = new File(template);
        StreamSource source = new StreamSource(new StringReader(xml.toString()));
        StreamSource transformSource = new StreamSource(xsltfile);
        
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        Transformer xslfoTransformer;
        try {
            xslfoTransformer = getTransformer(transformSource);
            Fop fop;
            try {
                fop = fopFactory.newFop(MimeConstants.MIME_PDF, outStream);

                Result res = new SAXResult(fop.getDefaultHandler());

                try {
                    xslfoTransformer.transform(source, res);
                    File pdffile = new File(output);
                    OutputStream out = new FileOutputStream(pdffile);  // Error is throwing on this line
                    out = new BufferedOutputStream(out);
                    FileOutputStream str = new FileOutputStream(pdffile);
                    str.write(outStream.toByteArray());
                    str.close();
                    out.close();

                } catch (TransformerException e) {
                    throw e;
                }
            } catch (FOPException e) {
                throw e;
            }
        } catch (TransformerConfigurationException e) {
            throw e;
        } catch (TransformerFactoryConfigurationError e) {
            throw e;
        }
    }

    private static Transformer getTransformer(StreamSource streamSource) {
        TransformerFactoryImpl impl
                = new TransformerFactoryImpl();

        try {
            return impl.newTransformer(streamSource);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private final String RESOURCES_DIR = "src//main//resources//template";
    private final String OUTPUT_DIR = "src//main//resources//output//";
    
    private StringBuilder xmlBuilder = new StringBuilder();

    public void convertToXml(Bestellung bestellung) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Games.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(bestellung, sw);
            xmlBuilder.append(sw.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void convertXmlToPdf(long userId) throws FileNotFoundException, FOPException, TransformerConfigurationException, TransformerException, IOException {
        File xsltFile = new File(RESOURCES_DIR + "//template.xsl");
        StreamSource xmlSource = new StreamSource(new StringReader(xmlBuilder.toString()));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        OutputStream out = new FileOutputStream(OUTPUT_DIR + "//output"+userId+".pdf");

        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }
}
